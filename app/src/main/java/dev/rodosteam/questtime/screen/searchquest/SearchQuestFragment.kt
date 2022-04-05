package dev.rodosteam.questtime.screen.searchquest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.rodosteam.questtime.quest.repo.QuestItemRepo
import dev.rodosteam.questtime.quest.repo.QuestItemRepoImpl
import dev.rodosteam.questtime.screen.common.base.BaseFragment

class SearchQuestFragment : BaseFragment() {

    private lateinit var presenter: SearchQuestPresenter
    private val questItemRepo: QuestItemRepo
        get() = app.findQuestItemRepo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = SearchQuestPresenter(
            screenNavigator = mainActivity.screenNavigator,
            backPressDispatcher = mainActivity,
            questItemRepo = questItemRepo,
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val view: SearchQuestMvpView = SearchQuestMvpViewImpl(inflater, container)
        presenter.bindView(view)
        return view.rootView
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    override fun onStop() {
        presenter.onStop()
        super.onStop()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    companion object {
        fun newInstance(): Fragment {
            return SearchQuestFragment()
        }
    }

}