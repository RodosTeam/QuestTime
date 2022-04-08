package dev.rodosteam.questtime.screen.editor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
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
            ViewModelProvider(this).get(EditorViewModel::class.java)

        _binding = FragmentEditorBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textEditor
        editorViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}