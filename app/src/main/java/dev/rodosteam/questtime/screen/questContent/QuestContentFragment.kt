package dev.rodosteam.questtime.screen.questContent

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.rodosteam.questtime.databinding.FragmentContentBinding
import dev.rodosteam.questtime.screen.common.base.BaseFragment
import dev.rodosteam.questtime.screen.preview.QuestPreviewFragment.Companion.QUEST_KEY

class QuestContentFragment : BaseFragment() {

    companion object {
        fun newInstance() = QuestContentFragment()
    }

    private lateinit var viewModel: QuestContentViewModel
    private var _binding: FragmentContentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[QuestContentViewModel::class.java]
        _binding = FragmentContentBinding.inflate(inflater, container, false)
        val id = arguments!!.getInt(QUEST_KEY)
        val quest = app.findQuestItemRepo.findById(id)
        quest?.let {
            // TODO do good
            mainActivity.supportActionBar?.title = it.title
            binding.fragmentContentContent.text = it.title
            binding.fragmentContentImage.setImageResource(it.iconId)
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[QuestContentViewModel::class.java]
        // TODO: Use the ViewModel
    }

}