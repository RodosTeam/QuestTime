package dev.rodosteam.questtime.ui.external

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dev.rodosteam.questtime.databinding.FragmentExternalBinding

class ExternalFragment : Fragment() {

    private lateinit var externalViewModel: ExternalViewModel
    private var _binding: FragmentExternalBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        externalViewModel =
            ViewModelProvider(this).get(ExternalViewModel::class.java)

        _binding = FragmentExternalBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textExternal
        externalViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}