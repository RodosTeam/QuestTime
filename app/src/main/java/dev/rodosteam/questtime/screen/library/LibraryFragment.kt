package dev.rodosteam.questtime.screen.library

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dev.rodosteam.questtime.databinding.FragmentLibraryBinding
import dev.rodosteam.questtime.screen.common.base.BaseFragment

class LibraryFragment : BaseFragment() {

    private lateinit var libraryViewModel: LibraryViewModel

    private var _binding: FragmentLibraryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        libraryViewModel = ViewModelProvider(this)[LibraryViewModel::class.java]
        _binding = FragmentLibraryBinding.inflate(inflater, container, false)
        val adapter = QuestItemAdapter(app.findQuestItemRepo.findAll(), findNavController())
        binding.libraryRecyclerView.adapter = adapter
        binding.libraryRecyclerView.layoutManager = LinearLayoutManager(this.context)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}