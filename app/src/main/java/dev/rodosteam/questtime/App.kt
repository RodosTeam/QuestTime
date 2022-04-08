package dev.rodosteam.questtime

import android.app.Application
import dev.rodosteam.questtime.quest.repo.meta.QuestMetaRepoMock

class App : Application() {

    val findQuestItemRepo by lazy {
        QuestMetaRepoMock()
    }

}