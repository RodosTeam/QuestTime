package dev.rodosteam.questtime

import android.app.Application
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dev.rodosteam.questtime.quest.repo.content.QuestContentRepo
import dev.rodosteam.questtime.quest.repo.content.QuestContentRepoJson
import dev.rodosteam.questtime.quest.repo.meta.QuestMetaFireBase
import dev.rodosteam.questtime.quest.repo.meta.QuestMetaRepo
import dev.rodosteam.questtime.quest.repo.meta.QuestMetaRepoJson
import dev.rodosteam.questtime.quest.repo.meta.QuestMetaRepoMock
import dev.rodosteam.questtime.utils.InternalStorage

class App : Application() {
    // Android internal storage
    val intStorage: InternalStorage by lazy {
        InternalStorage(applicationContext.filesDir)
    }

    val questMetaRepo: QuestMetaRepo by lazy {
        val repo = QuestMetaRepoMock()
        repo.addAll(QuestMetaRepoJson(intStorage).findAll())
        repo
    }

    val questContentRepo: QuestContentRepo by lazy {
        QuestContentRepoJson(questMetaRepo, intStorage)
    }

    val metaCloud: QuestMetaFireBase by lazy {
        QuestMetaFireBase()
    }

    val fireContentDb: DatabaseReference by lazy {
        FirebaseDatabase.getInstance().getReference("questsContent")
    }
}