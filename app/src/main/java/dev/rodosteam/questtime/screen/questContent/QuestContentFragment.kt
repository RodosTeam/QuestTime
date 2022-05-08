package dev.rodosteam.questtime.screen.questContent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import dev.rodosteam.questtime.databinding.FragmentContentBinding
import dev.rodosteam.questtime.quest.model.QuestContent
import dev.rodosteam.questtime.quest.model.Walkthrough
import dev.rodosteam.questtime.quest.repo.content.QuestContentRepoJson
import dev.rodosteam.questtime.screen.common.base.BaseFragment
import dev.rodosteam.questtime.screen.preview.QuestPreviewFragment.Companion.QUEST_KEY

class QuestContentFragment : BaseFragment() {

    companion object {
        fun newInstance() = QuestContentFragment()
    }

    private lateinit var viewModel: QuestContentViewModel
    private var _binding: FragmentContentBinding? = null
    private val binding get() = _binding!!
    private lateinit var buttons: List<Button> // TODO optimize
    private lateinit var textView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[QuestContentViewModel::class.java]
        _binding = FragmentContentBinding.inflate(inflater, container, false)
        val id = requireArguments().getInt(QUEST_KEY)
        val quest = app.questRepo.lastLoaded[id]

        textView = binding.fragmentContentText

        buttons = listOf(
            binding.fragmentContentButton1,
            binding.fragmentContentButton2,
            binding.fragmentContentButton3,
            binding.fragmentContentButton4
        )

        var content: QuestContent? = null
        quest?.let {
            // TODO do good
            mainActivity.supportActionBar?.title = it.title
            binding.fragmentContentContent.text = it.title
            Glide.with(binding.root)
                .load(quest.iconUrl)
                .centerCrop()
                .into(binding.fragmentContentImage)
            content = QuestContentRepoJson.readQuest(quest.contentJson)
        }

        if (content != null) {
            sync(Walkthrough(content!!))
        }

        return binding.root
    }

    private fun sync(walk: Walkthrough) {
        val choices = walk.page.choices.size

        textView.text = walk.page.displayText

        for (i in 0..3) {
            if (i < choices) {
                activateButton(i, walk)
            } else {
                deactivateButton(i)
            }
        }
    }

    private fun activateButton(order: Int, walk: Walkthrough) {
        val button = buttons[order]
        val text = walk.page.choices[order].displayText
        button.text = if (text.length > 24) text.substring(0, 23) else text // до 14 символов
        button.setOnClickListener { // чет сделать
            sync(walk.choose(order))
        }
        button.setOnLongClickListener { // открыть диалог для подсказки
            AlertDialog.Builder(requireContext()).setTitle(button.text).setMessage(text).show()
            true
        }
        button.visibility = View.VISIBLE
    }

    private fun deactivateButton(order: Int) {
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