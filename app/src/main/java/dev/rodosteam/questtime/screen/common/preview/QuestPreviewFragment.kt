package dev.rodosteam.questtime.screen.common.preview

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.rodosteam.questtime.R
import dev.rodosteam.questtime.screen.common.base.BaseFragment

class QuestPreviewFragment : BaseFragment() {

    companion object {
        fun newInstance() = QuestPreviewFragment()
    }

    private lateinit var viewModel: QuestPreviewViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_library_preview, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(QuestPreviewViewModel::class.java)
        // TODO: Use the ViewModel
    }

}