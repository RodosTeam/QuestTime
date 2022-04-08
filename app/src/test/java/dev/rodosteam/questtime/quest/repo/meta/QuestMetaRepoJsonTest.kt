package dev.rodosteam.questtime.quest.repo.meta

import dev.rodosteam.questtime.App
import org.junit.Assert
import org.junit.Test

class QuestMetaRepoJsonTest {
    companion object {
        const val QUEST_META_PATH = "quest_library/quest_information.json"

        const val TEST_QUEST_ID = -1
        const val TEST_QUEST_TITLE = "Test quest"
        const val TEST_QUEST_DESCRIPTION = "Test quest description"
        const val TEST_QUEST_AUTHOR = "Lev Saskov"
        const val TEST_QUEST_DOWNLOADS = 0
        const val TEST_QUEST_FAVORITES = 0
        const val TEST_QUEST_CREATED = 0L
        const val TEST_QUEST_FILENAME = "test_quest.json"
    }

    @Test
    fun reading_isCorrect() {
        val questRepo = QuestMetaRepoJson(TODO("access resources from tests"))
        val meta = questRepo.findById(TEST_QUEST_ID)
        Assert.assertNotNull(meta)
        Assert.assertEquals(TEST_QUEST_TITLE, meta?.title)
        Assert.assertEquals(TEST_QUEST_DESCRIPTION, meta?.description)
        Assert.assertEquals(TEST_QUEST_AUTHOR, meta?.author)
        Assert.assertEquals(TEST_QUEST_DOWNLOADS, meta?.downloads)
        Assert.assertEquals(TEST_QUEST_FAVORITES, meta?.favorites)
        Assert.assertEquals(TEST_QUEST_CREATED, meta?.created)
        Assert.assertEquals(TEST_QUEST_FILENAME, meta?.filename)
    }
}