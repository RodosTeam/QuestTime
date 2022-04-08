package dev.rodosteam.questtime.quest.repo.content

import android.content.res.Resources
import dev.rodosteam.questtime.R
import dev.rodosteam.questtime.quest.model.QuestContent
import dev.rodosteam.questtime.utils.asIterableOfJSONObjects
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener

class QuestContentRepoJson constructor(
    private val resources: Resources
) : QuestContentRepo {
    companion object {
        private const val START_NODE_ID = "startNodeId"
        private const val PAGES = "pages"
        private const val CHOICES = "choices"
        private const val ID = "id"
        private const val NEXT_PAGE_ID = "nextPageId"
        private const val DISPLAY_TEXT = "displayText"
        private val CONTENT_MAP = mapOf(-1 to R.raw.test_quest, 1 to R.raw.hobbit)
    }

    override fun findById(id: Int): QuestContent? = CONTENT_MAP[id]?.let { resId: Int ->
        resources.openRawResource(resId).bufferedReader().use { reader ->
            val jsonObject = JSONTokener(reader.readText()).nextValue() as JSONObject
            val jsonPages = jsonObject.getJSONArray(PAGES)
            QuestContent(
                readPages(jsonPages),
                QuestContent.Page.Id(jsonObject.getLong(START_NODE_ID))
            )
        }
    }

    private fun readPages(jsonPages: JSONArray): Iterable<QuestContent.Page> =
        jsonPages.asIterableOfJSONObjects().map {
            QuestContent.Page(
                QuestContent.Page.Id(it.getLong(ID)),
                it.getString(DISPLAY_TEXT),
                readChoices(it.getJSONArray(CHOICES))
            )
        }.toList()

    private fun readChoices(jsonChoices: JSONArray): List<QuestContent.Page.Choice> =
        jsonChoices.asIterableOfJSONObjects().map {
            QuestContent.Page.Choice(
                QuestContent.Page.Id(it.getLong(NEXT_PAGE_ID)),
                it.getString(DISPLAY_TEXT)
            )
        }.toList()
}