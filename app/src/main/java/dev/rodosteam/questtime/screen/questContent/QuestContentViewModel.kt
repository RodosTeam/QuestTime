package dev.rodosteam.questtime.screen.questContent

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.rodosteam.questtime.quest.model.QuestContent
import dev.rodosteam.questtime.quest.model.Walkthrough

class  QuestContentViewModel(var walkthrough: Walkthrough) : ViewModel() {

    val mutableLiveData = MutableLiveData<QuestContent.Page>()

    init {
        mutableLiveData.value = walkthrough.page
    }

    fun chooseWalk(order: Int) {
        walkthrough = walkthrough.choose(order)
        mutableLiveData.value = walkthrough.page
    }

}