package dev.rodosteam.questtime.screen.external

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import dev.rodosteam.questtime.databinding.FragmentExternalBinding
import dev.rodosteam.questtime.screen.common.base.BaseFragment

class ExternalFragment : BaseFragment() {

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

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}