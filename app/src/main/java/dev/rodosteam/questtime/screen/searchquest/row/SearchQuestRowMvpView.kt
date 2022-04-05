package dev.rodosteam.questtime.screen.searchquest.row

import dev.rodosteam.questtime.quest.model.QuestItem
import dev.rodosteam.questtime.screen.common.mvp.MvpView

interface SearchQuestRowMvpView : MvpView {

    interface Listener {
        fun onQuestItemClicked(questItemId: Int)
    }

    fun bindData(questItem: QuestItem)

}
