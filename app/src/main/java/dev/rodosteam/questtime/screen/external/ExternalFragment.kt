package dev.rodosteam.questtime.screen.external

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import dev.rodosteam.questtime.R
import dev.rodosteam.questtime.databinding.FragmentExternalBinding
import dev.rodosteam.questtime.screen.common.base.BaseFragmentWithOptionMenu

class ExternalFragment : BaseFragmentWithOptionMenu() {

    private lateinit var externalViewModel: ExternalViewModel
    private var _binding: FragmentExternalBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        externalViewModel = ViewModelProvider(this)[ExternalViewModel::class.java]
        _binding = FragmentExternalBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.top_search_menu, menu)
        val menuItem = menu.findItem(R.id.search_bar)
        val searchView = menuItem?.actionView as SearchView
        searchView.queryHint = this.getString(R.string.search_text)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}