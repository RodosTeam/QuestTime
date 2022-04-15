package dev.rodosteam.questtime.quest.repo.content

import dev.rodosteam.questtime.quest.model.QuestContent
import dev.rodosteam.questtime.utils.asIterableOfJSONObjects
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener
import java.io.File

class QuestContentFavorites(var favoriteDirectory: File) : QuestContentRepo {
    companion object {
        private const val START_NODE_ID = "startNodeId"
        private const val PAGES = "pages"
        private const val CHOICES = "choices"
        private const val ID = "id"
        private const val NEXT_PAGE_ID = "nextPageId"
        private const val DISPLAY_TEXT = "displayText"
    }

    override fun findById(id: Int): QuestContent? {
        if (favoriteDirectory.listFiles()?.size != 0) {
            for (contentFile in favoriteDirectory.listFiles()!!) {
                if (contentFile.name.equals("favoritesContent_$id.json")) {
                    contentFile.bufferedReader().use { reader ->
                        val jsonObject = JSONTokener(reader.readText()).nextValue() as JSONObject
                        val jsonPages = jsonObject.optJSONArray(PAGES)
                        val jsonId = jsonObject.optLong(START_NODE_ID)
                        val jsonName = jsonObject.opt("name")
                        if (jsonName == null || jsonPages == null) {
                            return null
                        }
                        return QuestContent(
                            readPages(jsonPages),
                            QuestContent.Page.Id(jsonId),
                            jsonName.toString()
                        )
                    }
                }
            }
        }
        return null
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