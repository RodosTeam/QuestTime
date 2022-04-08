package dev.rodosteam.questtime.quest.repo.content

import dev.rodosteam.questtime.quest.model.QuestContent
import dev.rodosteam.questtime.quest.repo.meta.QuestMetaRepo
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener
import java.io.File

class QuestContentRepoJson constructor(
    val questsMeta: QuestMetaRepo, val locationPath: String
) : QuestContentRepo {
    companion object {
        const val NAME = "name"
        const val START_NODE_ID = "startNodeId"
        const val PAGES = "pages"
        const val CHOICES = "choices"
        const val ID = "id"
        const val NEXT_PAGE_ID = "nextPageId"
        const val DISPLAY_TEXT = "displayText"
    }

    override fun findById(id: Int): QuestContent? {
        val questMeta = questsMeta.findById(id)
        if (questMeta != null) {
            return readQuestContent(questMeta.filename)
        }

        return null
    }

    override fun findByName(name: String): QuestContent? {
        return questsMeta.findByName(name)?.let { findById(it.id) }
    }

    private fun readQuestContent(filename: String): QuestContent? {
        val questFile = File(locationPath + filename)
        if (!questFile.canRead()) {
            throw NoSuchFileException(questFile)
        }

        val jsonObj = JSONTokener(questFile.readText(Charsets.UTF_8)).nextValue() as JSONObject
        val jsonPages = jsonObj.getJSONArray(PAGES)

        return QuestContent(
            readPages(jsonPages),
            QuestContent.Page.Id(jsonObj.getLong(START_NODE_ID))
        )
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