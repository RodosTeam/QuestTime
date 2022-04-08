package dev.rodosteam.questtime

import android.app.Application
import dev.rodosteam.questtime.quest.repo.content.QuestContentRepo
import dev.rodosteam.questtime.quest.repo.content.QuestContentRepoJson
import dev.rodosteam.questtime.quest.repo.meta.QuestMetaRepo
import dev.rodosteam.questtime.quest.repo.meta.QuestMetaRepoJson
import dev.rodosteam.questtime.quest.repo.meta.QuestMetaRepoMock

class App : Application() {

    val questMetaRepo: QuestMetaRepo by lazy {
        val repo = QuestMetaRepoMock()
        repo.addAll(QuestMetaRepoJson(resources).findAll())
        repo
    }

    val questContentRepo: QuestContentRepo by lazy {
        QuestContentRepoJson(resources)
    }
}