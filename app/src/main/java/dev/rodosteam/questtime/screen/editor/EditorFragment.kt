package dev.rodosteam.questtime.screen.editor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import dev.rodosteam.questtime.api.dto.QuestMetaDto
import dev.rodosteam.questtime.databinding.FragmentEditorBinding
import dev.rodosteam.questtime.screen.common.base.BaseFragment

class EditorFragment : BaseFragment() {

    private lateinit var editorViewModel: EditorViewModel
    private var _binding: FragmentEditorBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        editorViewModel =
            ViewModelProvider(this)[EditorViewModel::class.java]

        _binding = FragmentEditorBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.fragmentEditorCreateBtn.setOnClickListener {

            val id = binding.fragmentEditorIdEt.text.toString().toLong()
            val questName = binding.fragmentEditorNameEt.text.toString()
            val questDescription = binding.fragmentEditorDescriptionEt.text.toString()
            val questAuthor = binding.fragmentEditorAuthorEt.text.toString()
            val countOfDownloads = binding.fragmentEditorDownloadsEt.text.toString().toInt()
            val countOfFavorites = binding.fragmentEditorFavoritesEt.text.toString().toInt()
            val createdTime = System.currentTimeMillis()
            val iconImageUrl = binding.fragmentEditorImageUrlEt.text.toString()
            val jsonContent = binding.fragmentEditorJsonEt.text.toString()

            val questItemDto = QuestMetaDto(
                id,
                questName,
                questDescription,
                questAuthor,
                countOfDownloads,
                countOfFavorites,
                createdTime,
                iconImageUrl,
                jsonContent
            )
            app.metaCloud.fireMetaReference.push().setValue(questItemDto)

        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}