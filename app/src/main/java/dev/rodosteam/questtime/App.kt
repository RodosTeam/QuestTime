package dev.rodosteam.questtime

import android.app.Application
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dev.rodosteam.questtime.quest.database.QuestDao
import dev.rodosteam.questtime.quest.database.QuestDatabase
import dev.rodosteam.questtime.quest.database.QuestRepository
import dev.rodosteam.questtime.quest.repo.content.QuestContentRepo
import dev.rodosteam.questtime.quest.repo.content.QuestContentRepoJson
import dev.rodosteam.questtime.quest.repo.meta.QuestMetaFireBase
import dev.rodosteam.questtime.quest.repo.meta.QuestMetaRepo
import dev.rodosteam.questtime.quest.repo.meta.QuestMetaRepoJson
import dev.rodosteam.questtime.quest.repo.meta.QuestMetaRepoMock
import dev.rodosteam.questtime.utils.InternalStorage

class App : Application() {

    val questRepo: QuestRepository by lazy {
        val dao = QuestDatabase.getDataBase(applicationContext).questDao()
        QuestRepository(dao)
    }

    val metaCloud: QuestMetaFireBase by lazy {
        QuestMetaFireBase()
    }

}