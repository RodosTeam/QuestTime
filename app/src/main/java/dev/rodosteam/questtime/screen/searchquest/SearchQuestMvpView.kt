package dev.rodosteam.questtime.screen.searchquest

import dev.rodosteam.questtime.screen.common.mvp.MvpViewObservable
import dev.rodosteam.questtime.screen.common.nav.BackPressedListener

interface SearchQuestMvpView : MvpViewObservable<SearchQuestMvpView.Listener> {

    interface Listener : BackPressedListener {
        fun onFindButtonClicked()
    }

    fun showProgress()
    fun hideProgress()

}