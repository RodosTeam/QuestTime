package dev.rodosteam.questtime.screen.library

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dev.rodosteam.questtime.R
import dev.rodosteam.questtime.databinding.FragmentLibraryBinding
import dev.rodosteam.questtime.quest.database.Quest
import dev.rodosteam.questtime.quest.database.QuestDatabase
import dev.rodosteam.questtime.quest.database.QuestRepository
import dev.rodosteam.questtime.quest.model.QuestMeta
import dev.rodosteam.questtime.screen.common.base.BaseFragmentWithOptionMenu
import dev.rodosteam.questtime.screen.external.ExternalViewModel
import dev.rodosteam.questtime.utils.ViewModelFactory

class LibraryFragment : BaseFragmentWithOptionMenu() {

    private val viewModel: LibraryViewModel by viewModels { ViewModelFactory(app) }
    private var _binding: FragmentLibraryBinding? = null
    lateinit var adapter: QuestItemAdapter
    lateinit var quests: List<Quest>

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        app.metaCloud
        viewModel.getData()
        _binding = FragmentLibraryBinding.inflate(inflater, container, false)
        quests = viewModel.loaded
        adapter = QuestItemAdapter(quests, findNavController())
        binding.libraryRecyclerView.adapter = adapter
        binding.libraryRecyclerView.layoutManager = LinearLayoutManager(this.context)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.top_search_menu, menu)
        val menuItem = menu.findItem(R.id.search_bar)
        val searchView = menuItem?.actionView as SearchView
        searchView.queryHint = this.getString(R.string.search_text)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(p0: String?): Boolean {
                if (p0 == "") {
                    //quests.clear()
                    //quests.addAll(app.questRepo.readAllData)
                    adapter.notifyDataSetChanged()
                } else {
                    //quests.clear()
                    //TODO
                    //quests.addAll(app.questMetaRepo.findAllByName(p0.toString()))
                    adapter.notifyDataSetChanged()
                }
                return true
            }

            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }
        })

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}