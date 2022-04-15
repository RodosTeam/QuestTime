package dev.rodosteam.questtime.screen.preview

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import dev.rodosteam.questtime.R
import dev.rodosteam.questtime.databinding.FragmentLibraryPreviewBinding
import dev.rodosteam.questtime.quest.model.QuestMeta
import dev.rodosteam.questtime.screen.common.base.BaseFragment
import dev.rodosteam.questtime.screen.questContent.QuestContentFragment.Companion.REPO_LIBRARY_CONTENT
import org.json.JSONArray
import org.json.JSONObject
import java.io.File

class QuestPreviewFragment : BaseFragment() {

    companion object {

        const val QUEST_KEY = "quest"
        const val DOWNLOADED_KEY = "downloaded"
        const val REPO_LIBRARY_PREVIEW = "repo_library"

        fun newInstance() = QuestPreviewFragment()
    }

    private lateinit var viewModel: QuestPreviewViewModel
    private var _binding: FragmentLibraryPreviewBinding? = null
    private val binding get() = _binding!!
    var isLibrary = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[QuestPreviewViewModel::class.java]
        _binding = FragmentLibraryPreviewBinding.inflate(inflater, container, false)
        if (arguments == null) {
            return binding.root
        }
        val id = requireArguments().getInt(QUEST_KEY)
        isLibrary = requireArguments().getBoolean(REPO_LIBRARY_PREVIEW)
        var meta = app.questFavoritesMetaRepo
        if (isLibrary) {
            meta = app.questMetaRepo
        }
        val quest = meta.findById(id)
        quest?.let {
            // TODO do good
            mainActivity.supportActionBar?.title = it.title
            binding.fragmentPreviewImage.setImageResource(it.iconId)
            binding.fragmentPreviewTitle.text = it.title
            binding.fragmentPreviewDescription.text = it.description
            binding.fragmentPreviewAuthor.text = it.author
            binding.fragmentPreviewInfo.text = getString(R.string.downloads_info, it.downloads)
            val favoritesDirectory = context?.getExternalFilesDir(null)?.resolve("favorites")
            favoriteLogic(favoritesDirectory!!, quest)
        }
        //TODO вся логика должна быть в ViewModel но пока что так
        val downloaded = requireArguments().getBoolean(DOWNLOADED_KEY)
        if (downloaded) {
            if (quest == null) {
                findNavController().navigateUp()
                return binding.root
            }
            setQuestDownloaded(quest)
        } else {
            binding.fragmentPreviewLeftButton.text = getString(R.string.download_button)
            binding.fragmentPreviewPlayButton.visibility = View.GONE
        }

        return binding.root
    }

    private fun favoriteLogic(favoritesDirectory: File, quest : QuestMeta) {
        var isFavorite = checkQuestInFavoriteFolder(favoritesDirectory, quest.id)
        var favoriteResource = R.drawable.ic_favorite_red_24dp
        if (isFavorite) {
            favoriteResource = R.drawable.ic_favorite_red_filled_24dp
        }
        binding.fragmentPreviewFavorite.setImageResource(favoriteResource)
        binding.fragmentPreviewFavorite.setOnClickListener {
            favoriteResource = if (isFavorite) {
                deleteFavoriteFile(favoritesDirectory, quest.id)
                R.drawable.ic_favorite_red_24dp
            } else {
                addFavoriteFile(favoritesDirectory, quest)
                R.drawable.ic_favorite_red_filled_24dp
            }
            binding.fragmentPreviewFavorite.setImageResource(favoriteResource)
            isFavorite = !isFavorite
        }
    }

    private fun checkQuestInFavoriteFolder(favoritesDirectory: File, id : Int) : Boolean {
        val checkMeta = File(favoritesDirectory, "favoritesMeta_$id.json")
        val checkContent = File(favoritesDirectory, "favoritesContent_$id.json")
        return checkMeta.exists() && checkContent.exists()
    }

    private fun addFavoriteFile(favoritesDirectory: File, questMeta : QuestMeta) {
        saveMeta(favoritesDirectory, questMeta)
        saveContent(favoritesDirectory, questMeta.id)
        app.questFavoritesMetaRepo.add(questMeta)
    }

    private fun saveMeta(favoritesDirectory: File, questMeta : QuestMeta) {
        val favoritesMeta = File(favoritesDirectory, "favoritesMeta_" + questMeta.id + ".json")
        val jsonMeta = JSONObject()
        jsonMeta.put("id", questMeta.id)
        jsonMeta.put("title", questMeta.title)
        jsonMeta.put("description", questMeta.description)
        jsonMeta.put("author", questMeta.author)
        jsonMeta.put("downloads", questMeta.downloads)
        jsonMeta.put("favorites", questMeta.favorites)
        jsonMeta.put("created", questMeta.created)
        jsonMeta.put("iconId", questMeta.iconId)
        jsonMeta.put("filename", questMeta.filename)
        favoritesMeta.writeText(jsonMeta.toString())
    }

    private fun saveContent(favoritesDirectory: File, id: Int) {
        val favoritesContent = File(favoritesDirectory, "favoritesContent_$id.json")
        val questContent = app.questContentRepo.findById(id)
        val jsonContent = JSONObject()
        if (questContent != null) {
            jsonContent.put("name", questContent.name)
        } else {
            jsonContent.put("name", JSONObject())
        }
        if (questContent != null) {
            jsonContent.put("startNodeId", questContent.startingId)
        } else {
            jsonContent.put("startNodeId", JSONObject())
        }
        if (questContent?.pages?.entries == null) {
            jsonContent.put("pages", JSONObject())
        } else {
            val pagesArray = JSONArray()
            for (page in questContent.pages.entries) {
                val pageJson = JSONObject()
                pageJson.put("id", page.value.id)
                pageJson.put("displayText", page.value.displayText)
                val choicesArray = JSONArray()
                for (choice in page.value.choices) {
                    val choiceJson = JSONObject()
                    choiceJson.put("nextPageId", choice.nextPageId)
                    choiceJson.put("displayText", choice.displayText)
                    choicesArray.put(choiceJson)
                }
                pageJson.put("choices", choicesArray)
                pagesArray.put(pageJson)
            }
            jsonContent.put("pages", pagesArray)
        }
        favoritesContent.writeText(jsonContent.toString())
    }

    private fun deleteFavoriteFile(favoritesDirectory: File, id : Int) {
        val favoritesMeta = File(favoritesDirectory, "favoritesMeta_$id.json")
        favoritesMeta.delete()
        val favoritesContent = File(favoritesDirectory, "favoritesContent_$id.json")
        favoritesContent.delete()
        app.questFavoritesMetaRepo.remove(id)
    }

    private fun setQuestDownloaded(quest: QuestMeta) {
        binding.fragmentPreviewLeftButton.text = getString(R.string.delete_button)
        binding.fragmentPreviewPlayButton.visibility = View.VISIBLE
        binding.fragmentPreviewPlayButton.setOnClickListener {
            findNavController().navigate(
                R.id.action_questPreviewFragment_to_questContentFragment,
                bundleOf(
                    QUEST_KEY to (quest.id),
                    REPO_LIBRARY_CONTENT to isLibrary
                )
            )
        }
        binding.fragmentPreviewLeftButton.setOnClickListener {
            app.questMetaRepo.remove(quest.id)
            setQuestDeleted(quest)
        }
    }

    private fun setQuestDeleted(quest: QuestMeta) {
        binding.fragmentPreviewLeftButton.text = getString(R.string.download_button)
        binding.fragmentPreviewPlayButton.visibility = View.GONE
        binding.fragmentPreviewLeftButton
        binding.fragmentPreviewLeftButton.setOnClickListener {
            downloadQuest(quest)
        }
    }

    private fun downloadQuest(quest: QuestMeta) {
        app.questMetaRepo.add(quest)
        setQuestDownloaded(quest)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[QuestPreviewViewModel::class.java]
        // TODO: Use the ViewModel
    }

}