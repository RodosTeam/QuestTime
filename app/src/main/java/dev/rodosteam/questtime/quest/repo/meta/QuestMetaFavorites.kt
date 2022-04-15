package dev.rodosteam.questtime.quest.repo.meta;

import dev.rodosteam.questtime.quest.model.QuestMeta
import org.json.JSONObject
import org.json.JSONTokener
import java.io.File

class QuestMetaFavorites(favoriteDirectory : File) : QuestMetaRepoBase() {
    companion object {
        private const val ID = "id"
        private const val TITLE = "title"
        private const val DESCRIPTION = "description"
        private const val AUTHOR = "author"
        private const val DOWNLOADS = "downloads"
        private const val FAVORITES = "favorites"
        private const val CREATED = "created"
        private const val ICONID = "iconId"
        private const val FILENAME = "filename"

        private fun readMeta(jsonMeta: JSONObject): QuestMeta =
                QuestMeta(
                    jsonMeta.getInt(ID),
                    jsonMeta.getString(TITLE),
                    jsonMeta.getString(DESCRIPTION),
                    jsonMeta.getString(AUTHOR),
                    jsonMeta.getInt(DOWNLOADS),
                    jsonMeta.getInt(FAVORITES),
                    jsonMeta.getLong(CREATED),
                    jsonMeta.getInt(ICONID),
                    jsonMeta.getString(FILENAME)
                )
    }

    init {
        if (favoriteDirectory.listFiles()?.size != 0) {
            for (metaFile in favoriteDirectory.listFiles()!!) {
                if (metaFile.name.startsWith("favoritesMeta")) {
                    metaFile.bufferedReader().use { reader ->
                        val jsonObject = JSONTokener(reader.readText()).nextValue() as JSONObject
                        add(readMeta(jsonObject))
                    }
                }
            }
        }
    }
}
