package dev.rodosteam.questtime.screen.preview

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import dev.rodosteam.questtime.R
import dev.rodosteam.questtime.databinding.FragmentLibraryPreviewBinding
import dev.rodosteam.questtime.quest.database.Quest
import dev.rodosteam.questtime.quest.database.getQuestFromMeta
import dev.rodosteam.questtime.quest.model.QuestMeta
import dev.rodosteam.questtime.screen.common.base.BaseFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class QuestPreviewFragment : BaseFragment() {

    companion object {

        const val QUEST_KEY = "quest"
        const val DOWNLOADED_KEY = "downloaded"

        fun newInstance() = QuestPreviewFragment()
    }

    private lateinit var viewModel: QuestPreviewViewModel
    private var _binding: FragmentLibraryPreviewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[QuestPreviewViewModel::class.java]
        _binding = FragmentLibraryPreviewBinding.inflate(inflater, container, false)
        if (arguments == null) {
            return binding.root
        }

        //TODO вся логика должна быть в ViewModel но пока что так
        val quest: Quest?
        val id = requireArguments().getInt(QUEST_KEY)
        val downloaded = requireArguments().getBoolean(DOWNLOADED_KEY)
        if (downloaded) {
            quest = app.questRepo.lastLoaded[id]
            if (quest == null) {
                findNavController().navigateUp()
                return binding.root
            }
            setQuestDownloaded(quest)
        } else {
            val meta = app.metaCloud.findById(id)
            if (meta == null) {
                findNavController().navigateUp()
                return binding.root
            }
            quest = getQuestFromMeta(meta)
            setQuestDeleted(quest)
        }

        quest.let {
            // TODO do good
            mainActivity.supportActionBar?.title = it.title
            Glide.with(binding.root)
                .load(quest.iconUrl)
                .centerCrop()
                .into(binding.fragmentPreviewImage)
            binding.fragmentPreviewTitle.text = it.title
            binding.fragmentPreviewDescription.text = it.description
            binding.fragmentPreviewAuthor.text = it.author
            binding.fragmentPreviewInfo.text = getString(R.string.downloads_info, it.downloads)
        }

        return binding.root
    }

    private fun setQuestDownloaded(quest: Quest) {
        binding.fragmentPreviewLeftButton.text = getString(R.string.delete_button)
        binding.fragmentPreviewPlayButton.visibility = View.VISIBLE
        binding.fragmentPreviewPlayButton.setOnClickListener {
            findNavController().navigate(
                R.id.action_questPreviewFragment_to_questContentFragment,
                bundleOf(QUEST_KEY to (quest.id))
            )
        }
        binding.fragmentPreviewLeftButton.setOnClickListener {
            app.questMetaRepo.remove(quest.id)
            setQuestDeleted(quest)
        }
    }

    private fun setQuestDeleted(quest: Quest) {
        lifecycleScope.launch(Dispatchers.IO) {
            app.questRepo.removeQuest(quest.id)
            app.questRepo.readAllData()
        }
        binding.fragmentPreviewLeftButton.text = getString(R.string.download_button)
        binding.fragmentPreviewPlayButton.visibility = View.GONE
        binding.fragmentPreviewLeftButton
        binding.fragmentPreviewLeftButton.setOnClickListener {
            downloadQuest(quest)
        }
    }

    private fun downloadQuest(quest: Quest) {
        lifecycleScope.launch(Dispatchers.IO) {
            app.questRepo.addQuest(quest)
            app.questRepo.readAllData()
        }
        setQuestDownloaded(quest)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[QuestPreviewViewModel::class.java]
        // TODO: Use the ViewModel
    }

}