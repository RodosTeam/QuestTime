package dev.rodosteam.questtime

import android.app.Application
import dev.rodosteam.questtime.quest.repo.content.QuestContentFavorites
import dev.rodosteam.questtime.quest.repo.content.QuestContentRepo
import dev.rodosteam.questtime.quest.repo.content.QuestContentRepoJson
import dev.rodosteam.questtime.quest.repo.meta.QuestMetaFavorites
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

    val questFavoritesMetaRepo: QuestMetaRepo by lazy {
        val favoritesDirectory = this.getExternalFilesDir(null)?.resolve("favorites")
        favoritesDirectory?.mkdir()
        QuestMetaFavorites(favoritesDirectory!!)
    }

    val questFavoritesContentRepo: QuestContentRepo by lazy {
        val favoritesDirectory = this.getExternalFilesDir(null)?.resolve("favorites")
        favoritesDirectory?.mkdir()
        QuestContentFavorites(favoritesDirectory!!)
    }
}