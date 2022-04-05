package dev.rodosteam.questtime.screen.searchquest

import dev.rodosteam.questtime.quest.repo.QuestItemRepo
import dev.rodosteam.questtime.screen.common.mvp.MvpPresenter
import dev.rodosteam.questtime.screen.common.nav.BackPressDispatcher
import dev.rodosteam.questtime.screen.common.nav.ScreenNavigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class SearchQuestPresenter(
    private val screenNavigator: ScreenNavigator,
    private val backPressDispatcher: BackPressDispatcher,
    private val questItemRepo: QuestItemRepo
) : MvpPresenter<SearchQuestMvpView>, SearchQuestMvpView.Listener {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private lateinit var view: SearchQuestMvpView

    override fun bindView(view: SearchQuestMvpView) {
        this.view = view
    }

    override fun onStart() {
        TODO("Not yet implemented")
    }

    override fun onStop() {
        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        TODO("Not yet implemented")
    }

    override fun onFindButtonClicked() {
        coroutineScope.launch {
           TODO()
        }
    }

    override fun onBackPressed(): Boolean {
        TODO("Not yet implemented")
    }

}