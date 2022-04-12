package dev.rodosteam.questtime.quest.repo.content

import dev.rodosteam.questtime.quest.model.QuestContent
import dev.rodosteam.questtime.quest.repo.meta.QuestMetaRepo
import dev.rodosteam.questtime.utils.InternalStorage
import dev.rodosteam.questtime.utils.asIterableOfJSONObjects
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener

class QuestContentRepoJson(
    private val metaRepo: QuestMetaRepo,
    private val intStorage: InternalStorage
) : QuestContentRepo {
    companion object {
        // Json primitives
        private const val START_NODE_ID = "startNodeId"
        private const val PAGES = "pages"
        private const val CHOICES = "choices"
        private const val ID = "id"
        private const val NEXT_PAGE_ID = "nextPageId"
        private const val DISPLAY_TEXT = "displayText"
    }

    override fun findById(id: Int): QuestContent? {
        val meta = metaRepo.findById(id) ?: return null
        val filename = metaRepo.findById(id)!!.filename

        val jsonObject = JSONTokener(intStorage.read(filename)).nextValue() as JSONObject
        val jsonPages = jsonObject.getJSONArray(PAGES)
        return QuestContent(
            readPages(jsonPages),
            QuestContent.Page.Id(jsonObject.getLong(START_NODE_ID))
        )
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