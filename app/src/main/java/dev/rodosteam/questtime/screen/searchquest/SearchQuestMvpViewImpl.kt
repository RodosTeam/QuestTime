package dev.rodosteam.questtime.screen.searchquest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import dev.rodosteam.questtime.R
import dev.rodosteam.questtime.quest.model.QuestItem
import dev.rodosteam.questtime.screen.common.mvp.MvpViewObservableBase
import dev.rodosteam.questtime.screen.searchquest.row.SearchQuestRowMvpView
import dev.rodosteam.questtime.screen.searchquest.row.SearchQuestRowMvpViewImpl

class SearchQuestMvpViewImpl(layoutInflater: LayoutInflater, parent: ViewGroup?) :
    MvpViewObservableBase<SearchQuestMvpView.Listener>(), SearchQuestMvpView,
    SearchQuestRowMvpView.Listener {

    override var rootView: View =
        layoutInflater.inflate(R.layout.search_quest_fragment, parent, false)
    private val searchField: EditText = findViewById(R.id.search_fragment_search_field)
    private val btnGoBack: Button = findViewById(R.id.search_fragment_find_quest_button)
    private val rvBlogItems: RecyclerView = findViewById(R.id.founded_quests_recycler_view)
    private val blogItemsAdapter = SearchQuestAdapter(layoutInflater, this)

    init {
        btnGoBack.setOnClickListener { find() }
    }

    private fun find() {
        listeners.forEach { it.onFindButtonClicked() }
    }

    override fun bindData(blogItems: List<QuestItem>) {
        blogItemsAdapter.bindData(blogItems)
    }

    override fun onQuestItemClicked(questItemId: Int) {
        for (listener in listeners) {
            listener.onQuestItemClicked(questItemId)
        }
    }


    override fun showProgress() {
        //TODO
    }

    override fun hideProgress() {
        //TODO
    }

}