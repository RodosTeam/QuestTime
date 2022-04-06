package dev.rodosteam.questtime.screen.common.preview

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.rodosteam.questtime.R
import dev.rodosteam.questtime.databinding.FragmentLibraryBinding
import dev.rodosteam.questtime.databinding.FragmentLibraryPreviewBinding
import dev.rodosteam.questtime.screen.common.base.BaseFragment
import dev.rodosteam.questtime.screen.common.library.LibraryViewModel

class QuestPreviewFragment : BaseFragment() {

    companion object {
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
        val id = arguments!!.getInt("quest")
        val quest = app.findQuestItemRepo.findById(id)
        quest?.let {
            binding.fragmentLibraryPreviewTitle.text = it.title
            binding.fragmentLibraryPreviewDescription.text = it.description
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[QuestPreviewViewModel::class.java]
        // TODO: Use the ViewModel
    }

}