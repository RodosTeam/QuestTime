package dev.rodosteam.questtime.screen.questContent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import dev.rodosteam.questtime.databinding.FragmentContentBinding
import dev.rodosteam.questtime.quest.model.QuestContent
import dev.rodosteam.questtime.quest.repo.content.QuestContentRepoJson
import dev.rodosteam.questtime.screen.common.base.BaseFragment
import dev.rodosteam.questtime.screen.preview.QuestPreviewFragment.Companion.QUEST_KEY
import dev.rodosteam.questtime.utils.ViewModelFactory


class QuestContentFragment : BaseFragment() {

    private val viewModel: QuestContentViewModel by viewModels {
        ViewModelFactory(
            app,
            app.questRepo.lastLoaded[requireArguments().getInt(QUEST_KEY)]
        )
    }
    private var _binding: FragmentContentBinding? = null
    private val binding get() = _binding!!
    private lateinit var buttons: List<Button> // TODO optimize
    private lateinit var textView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        enterFullscreen()

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
            Glide.with(binding.root)
                .load(quest.iconUrl)
                .into(binding.fragmentContentImage)
            content = QuestContentRepoJson.readQuest(quest.contentJson)
        }

        viewModel.mutableLiveData.observe(this) {
            sync(it)
        }

        return binding.root
    }

    private fun sync(page: QuestContent.Page) {
        val choices = page.choices.size

        textView.text = page.displayText
        Glide.with(binding.root)
            .load(page.imageUrl)
            .into(binding.fragmentContentImage)

        for (i in 0..3) {
            if (i < choices) {
                activateButton(i, page)
            } else {
                deactivateButton(i)
            }
        }
    }

    private fun activateButton(order: Int, page: QuestContent.Page) {
        val button = buttons[order]
        button.text = page.choices[order].displayText
        button.setOnClickListener { // ?????? ??????????????
            viewModel.chooseWalk(order)
        }
        /*
        button.setOnLongClickListener { // ?????????????? ???????????? ?????? ??????????????????
            AlertDialog.Builder(requireContext()).setTitle(button.text).setMessage(text).show()
            true
        }
        */
        button.visibility = View.VISIBLE
    }

    private fun deactivateButton(order: Int) {
        val button = buttons[order]
        button.text = ""
        button.visibility = View.GONE
    }

    override fun onStop() {
        super.onStop()
        exitFullscreen()
    }

    private fun enterFullscreen() {
        val a = (activity as AppCompatActivity?)!!
        a.supportActionBar!!.hide()
    }

    private fun exitFullscreen() {
        val a = (activity as AppCompatActivity?)!!
        a.supportActionBar!!.show()
    }

}