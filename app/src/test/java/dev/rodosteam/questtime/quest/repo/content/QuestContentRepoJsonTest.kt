package dev.rodosteam.questtime.quest.repo.content

import dev.rodosteam.questtime.quest.model.QuestContent
import dev.rodosteam.questtime.quest.repo.meta.QuestMetaRepoJson
import dev.rodosteam.questtime.utils.InternalStorage
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class QuestContentRepoJsonTest {
    companion object {
        private const val TEST_ID = -1
        private const val TEST_FILES_DIR = "../test_files"
        private val FIRST = QuestContent.Page.Id(1)
    }

    @Test
    fun reading_isCorrect() {
        val intStorage = InternalStorage(File(TEST_FILES_DIR))
        val questMetaRepo = QuestMetaRepoJson(intStorage)
        val questContentRepo = QuestContentRepoJson(questMetaRepo, intStorage)
        val quest = questContentRepo.findById(TEST_ID)
        assertNotNull(quest)
        assertNotNull(quest.pages)
        assertEquals(quest.pages.size, 3)

        val firstPage = quest.pages[FIRST]
        assertNotNull(firstPage)
        assertNotNull(firstPage.choices)
        assertEquals(firstPage.choices.size, 2)
    }
}