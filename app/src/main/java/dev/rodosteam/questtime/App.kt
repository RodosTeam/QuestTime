package dev.rodosteam.questtime

import android.app.Application
import dev.rodosteam.questtime.quest.repo.QuestItemRepoImpl

class App : Application() {

    val findQuestItemRepo by lazy {
        QuestItemRepoImpl()
    }

}