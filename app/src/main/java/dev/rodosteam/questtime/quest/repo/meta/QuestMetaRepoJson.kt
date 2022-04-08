package dev.rodosteam.questtime.quest.repo.meta

import android.content.res.Resources
import dev.rodosteam.questtime.R
import dev.rodosteam.questtime.quest.model.QuestMeta
import dev.rodosteam.questtime.utils.asIterableOfJSONObjects
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener

class QuestMetaRepoJson(resources: Resources) : QuestMetaRepoBase() {
    companion object {
        private const val QUESTS_META_ARRAY = "questsMeta"
        private const val ID = "id"
        private const val TITLE = "title"
        private const val DESCRIPTION = "description"
        private const val AUTHOR = "author"
        private const val DOWNLOADS = "downloads"
        private const val FAVORITES = "favorites"
        private const val CREATED = "created"
        private const val FILENAME = "filename"
        private val IMAGES_MAP = mapOf(-1 to R.drawable.test_icon, 1 to R.drawable.hobbit_lego_icon)

        private fun readMetas(jsonMetas: JSONArray): Iterable<QuestMeta> =
            jsonMetas.asIterableOfJSONObjects().map { currentJson ->
                val metaId = currentJson.getInt(ID)
                QuestMeta(
                    metaId,
                    currentJson.getString(TITLE),
                    currentJson.getString(DESCRIPTION),
                    currentJson.getString(AUTHOR),
                    currentJson.getInt(DOWNLOADS),
                    currentJson.getInt(FAVORITES),
                    currentJson.getLong(CREATED),
                    IMAGES_MAP.getValue(metaId),
                    currentJson.getString(FILENAME)
                )
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