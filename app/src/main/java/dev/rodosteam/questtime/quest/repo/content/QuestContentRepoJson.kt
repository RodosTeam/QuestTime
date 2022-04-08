package dev.rodosteam.questtime.quest.repo.content

import android.content.res.Resources
import dev.rodosteam.questtime.R
import dev.rodosteam.questtime.quest.model.QuestContent
import dev.rodosteam.questtime.quest.repo.meta.QuestMetaRepo
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener

class QuestContentRepoJson constructor(
    private val resources: Resources
) : QuestContentRepo {
    companion object {
        const val NAME = "name"
        const val START_NODE_ID = "startNodeId"
        const val PAGES = "pages"
        const val CHOICES = "choices"
        const val ID = "id"
        const val NEXT_PAGE_ID = "nextPageId"
        const val DISPLAY_TEXT = "displayText"
        private val CONTENT_MAP = mapOf(-1 to R.raw.test_quest, 1 to R.raw.hobbit)
    }

    override fun findById(id: Int): QuestContent? {
        if (!CONTENT_MAP.containsKey(id)) {
            return null
        }
        resources.openRawResource(CONTENT_MAP.getValue(id)).bufferedReader().use {
            val jsonObject = JSONTokener(it.readText()).nextValue() as JSONObject
            val jsonPages = jsonObject.getJSONArray(PAGES)
            return QuestContent(
                readPages(jsonPages),
                QuestContent.Page.Id(jsonObject.getLong(START_NODE_ID))
            )
        }
    }

    override fun findByName(name: String): QuestContent? {
        throw UnsupportedOperationException()
    }

    private fun readPages(jsonPages: JSONArray): Iterable<QuestContent.Page> {
        val pages = arrayListOf<QuestContent.Page>()
        for (i in 0 until jsonPages.length()) {
            val curJsonPage = jsonPages.getJSONObject(i)
            pages.add(
                QuestContent.Page(
                    QuestContent.Page.Id(curJsonPage.getLong(ID)),
                    curJsonPage.getString(DISPLAY_TEXT),
                    readChoices(curJsonPage.getJSONArray(CHOICES))
                )
            )
        }
        return pages
    }

    private fun readChoices(jsonChoices: JSONArray): List<QuestContent.Page.Choice> {
        val choices = arrayListOf<QuestContent.Page.Choice>()
        for (i in 0 until jsonChoices.length()) {
            val curJsonChoice = jsonChoices.getJSONObject(i)
            choices.add(
                QuestContent.Page.Choice(
                    QuestContent.Page.Id(curJsonChoice.getLong(NEXT_PAGE_ID)),
                    curJsonChoice.getString(DISPLAY_TEXT)
                )
            )
        }
        return choices
    }
}