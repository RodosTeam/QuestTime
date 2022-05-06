package dev.rodosteam.questtime.screen.external

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dev.rodosteam.questtime.R
import dev.rodosteam.questtime.databinding.FragmentExternalBinding
import dev.rodosteam.questtime.quest.model.QuestMeta
import dev.rodosteam.questtime.screen.common.base.BaseFragmentWithOptionMenu
import dev.rodosteam.questtime.utils.ViewModelFactory


class ExternalFragment : BaseFragmentWithOptionMenu() {

    private val viewModel: ExternalViewModel by viewModels { ViewModelFactory(app) }
    private var _binding: FragmentExternalBinding? = null
    lateinit var adapter: QuestItemAdapter
    lateinit var quests: MutableList<QuestMeta>

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentExternalBinding.inflate(inflater, container, false)
        val downloaded = app.questRepo.lastLoaded
        quests = app.metaCloud.findAll().filter { it.id !in downloaded.keys  }.toMutableList()
        adapter = QuestItemAdapter(quests, findNavController(), viewModel)
        binding.externalRecyclerView.adapter = adapter
        binding.externalRecyclerView.layoutManager = LinearLayoutManager(this.context)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.top_search_menu, menu)
        val menuItem = menu.findItem(R.id.search_bar)
        val searchView = menuItem?.actionView as SearchView
        searchView.queryHint = this.getString(R.string.search_text)

//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            val downloaded = app.questRepo.lastLoaded
//
//            override fun onQueryTextChange(p0: String?): Boolean {
//                if (p0 == "") {
//                    quests.clear()
//
//                    quests = app.metaCloud.findAll().filter { it.id !in downloaded.keys  }.toMutableList()
//                    adapter.notifyDataSetChanged()
//                } else {
//                    quests.clear()
//                    quests = app.metaCloud.findAll().filter { it.id !in downloaded.keys  }.toMutableList()
//                    adapter.notifyDataSetChanged()
//                }
//                return true
//            }
//
//            override fun onQueryTextSubmit(p0: String?): Boolean {
//                return true
//            }
//        })

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}