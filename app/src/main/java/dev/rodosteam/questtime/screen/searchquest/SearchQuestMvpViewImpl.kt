package dev.rodosteam.questtime.screen.searchquest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import dev.rodosteam.questtime.R
import dev.rodosteam.questtime.screen.common.mvp.MvpViewObservableBase

class SearchQuestMvpViewImpl(layoutInflater: LayoutInflater, parent: ViewGroup?) :
    MvpViewObservableBase<SearchQuestMvpView.Listener>(), SearchQuestMvpView {

    override var rootView: View =
        layoutInflater.inflate(R.layout.search_quest_fragment, parent, false)
    private val searchField: EditText = findViewById(R.id.search_fragment_search_field)
    private val btnGoBack: Button = findViewById(R.id.search_fragment_find_quest_button)

    init {
        btnGoBack.setOnClickListener { find() }
    }

    private fun find() {
        listeners.forEach { it.onFindButtonClicked() }
    }


    override fun showProgress() {
        //TODO
    }

    override fun hideProgress() {
        //TODO
    }


}