package dev.rodosteam.questtime.screen.questContent

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import dev.rodosteam.questtime.databinding.FragmentContentBinding
import dev.rodosteam.questtime.quest.model.Walkthrough
import dev.rodosteam.questtime.screen.common.base.BaseFragment
import dev.rodosteam.questtime.screen.preview.QuestPreviewFragment.Companion.QUEST_KEY

class QuestContentFragment : BaseFragment() {

    companion object {
        fun newInstance() = QuestContentFragment()
    }

    private lateinit var viewModel: QuestContentViewModel
    private var _binding: FragmentContentBinding? = null
    private val binding get() = _binding!!
    private lateinit var buttons : List<Button> // TODO optimize

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[QuestContentViewModel::class.java]
        _binding = FragmentContentBinding.inflate(inflater, container, false)
        val id = arguments!!.getInt(QUEST_KEY)
        val quest = app.findQuestItemRepo.findById(id)

        buttons = listOf(
            binding.fragmentContentButton1,
            binding.fragmentContentButton2,
            binding.fragmentContentButton3,
            binding.fragmentContentButton4
        )

        quest?.let {
            // TODO do good
            mainActivity.supportActionBar?.title = it.title
            binding.fragmentContentContent.text = it.title
            binding.fragmentContentImage.setImageResource(it.iconId)

        }

        // TODO sync()

        return binding.root
    }

    private fun sync(walk: Walkthrough) {
        val choices = walk.page.choices.size
        for(i in 0..3) {
            if( i < choices) {
                activateButton(i, walk)
            } else {
                deactivateButton(i)
            }
        }
    }

    private fun activateButton(order : Int, walk: Walkthrough) {
        val button = buttons[order]
        button.text = walk.page.choices[order].displayText //ну надо чет написать
        button.setOnClickListener{ // чет сделать
            walk.choose(order)
            sync(walk)
        }
        button.visibility = View.VISIBLE
    }

    private fun deactivateButton(order : Int) {
        val button = buttons[order]
        button.text = ""
        button.visibility = View.GONE
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[QuestContentViewModel::class.java]
        // TODO: Use the ViewModel
    }

}