package dev.rodosteam.questtime.quest.repo.meta

import dev.rodosteam.questtime.R
import dev.rodosteam.questtime.quest.model.QuestMeta
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener
import java.io.File

class QuestMetaRepoJson : QuestMetaRepoBase() {
    companion object {
        const val QUESTS_META_ARRAY = "questsMeta"
        const val ID = "id"
        const val TITLE = "title"
        const val DESCRIPTION = "description"
        const val AUTHOR = "author"
        const val DOWNLOADS = "downloads"
        const val FAVORITES = "favorites"
        const val CREATED = "created"
        const val FILENAME = "filename"
        private val IMAGES_MAP = mapOf(-1 to R.drawable.test_icon, 1 to R.drawable.hobbit_lego_icon)

        fun load(filePath: String): QuestMetaRepoJson {
            val questMetaFile = File(filePath)
            if (!questMetaFile.canRead()) {
                throw NoSuchFileException(questMetaFile)
            }

            val jsonObj =
                JSONTokener(questMetaFile.readText(Charsets.UTF_8)).nextValue() as JSONObject
            val jsonMetas = jsonObj.getJSONArray(QUESTS_META_ARRAY)

            val questMetaRepo = QuestMetaRepoJson()
            questMetaRepo.addAll(readMetas(jsonMetas))
            return questMetaRepo
        }

        private fun readMetas(jsonMetas: JSONArray): Map<Int, QuestMeta> {
            val metas = mutableMapOf<Int, QuestMeta>()
            for (i in 0 until jsonMetas.length()) {
                val curJsonMeta = jsonMetas.getJSONObject(i)

                //TODO: Can be refactored
                val id = curJsonMeta.getInt(ID)
                metas[id] = QuestMeta(
                    id,
                    curJsonMeta.getString(TITLE),
                    curJsonMeta.getString(DESCRIPTION),
                    curJsonMeta.getString(AUTHOR),
                    curJsonMeta.getInt(DOWNLOADS),
                    curJsonMeta.getInt(FAVORITES),
                    curJsonMeta.getLong(CREATED),
                    IMAGES_MAP.getValue(id),
                    curJsonMeta.getString(FILENAME)
                )
            }

            return metas
        }
    }
}