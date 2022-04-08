package dev.rodosteam.questtime

import android.app.Application
import dev.rodosteam.questtime.quest.repo.content.QuestContentRepoJson
import dev.rodosteam.questtime.quest.repo.meta.QuestMetaRepoJson
import dev.rodosteam.questtime.quest.repo.meta.QuestMetaRepoMock

class App : Application() {

    val findQuestItemRepo by lazy {
        QuestMetaRepoMock()
    }

    val findQuestContentRepoJson: QuestContentRepoJson by lazy {
        QuestContentRepoJson(resources)
    }

    val findQuestMetaRepoJson: QuestMetaRepoJson by lazy {
        QuestMetaRepoJson(resources)
    }

}