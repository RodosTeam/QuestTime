package dev.rodosteam.questtime.screen.searchquest

import dev.rodosteam.questtime.quest.repo.QuestItemRepo
import dev.rodosteam.questtime.screen.common.mvp.MvpPresenter
import dev.rodosteam.questtime.screen.common.nav.BackPressDispatcher
import dev.rodosteam.questtime.screen.common.nav.ScreenNavigator
import kotlinx.coroutines.*

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
        view.registerListener(this)
        backPressDispatcher.registerListener(this)
    }

    override fun onStop() {
        view.unregisterListener(this)
        backPressDispatcher.unregisterListener(this)
    }

    override fun onDestroy() {
        coroutineScope.coroutineContext.cancelChildren()
    }

    override fun onFindButtonClicked() {
        coroutineScope.launch {
           TODO()
        }
    }

    override fun onQuestItemClicked(questItemId: Int) {
        screenNavigator.toQuest(questItemId)
    }

    override fun onBackPressed(): Boolean {
        screenNavigator.navigateUp()
        return true
    }

}