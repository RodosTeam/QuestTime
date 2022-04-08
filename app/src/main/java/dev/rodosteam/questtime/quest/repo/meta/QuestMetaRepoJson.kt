package dev.rodosteam.questtime.quest.repo.meta

import android.content.res.Resources
import dev.rodosteam.questtime.R
import dev.rodosteam.questtime.quest.model.QuestMeta
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener

class QuestMetaRepoJson(resources: Resources) : QuestMetaRepoBase() {
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

    init {
        resources.openRawResource(R.raw.quest_information).bufferedReader().use {
            val jsonObject = JSONTokener(it.readText()).nextValue() as JSONObject
            val jsonMetas = jsonObject.getJSONArray(QUESTS_META_ARRAY)
            addAll(readMetas(jsonMetas))
        }
    }
}