package dev.rodosteam.questtime.quest.repo.content

import dev.rodosteam.questtime.quest.model.QuestContent
import dev.rodosteam.questtime.quest.model.QuestMeta
import dev.rodosteam.questtime.quest.repo.meta.QuestMetaRepoBase
import org.junit.Assert
import org.junit.Test

class QuestContentRepoJsonTest {
    companion object {
        const val QUEST_CONTENT_REPO_LOCATION = "quest_library/quests/"
        const val QUEST_FILENAME = "test_quest.json"
        const val TEST_ID = -1
        val FIRST = QuestContent.Page.Id(1)
    }

    @Test
    fun reading_isCorrect() {
        val meta = QuestMetaRepoBase()
        meta.add(QuestMeta(TEST_ID, "", "", "", 0, 0, 0, QUEST_FILENAME))
        val questContentRepo = QuestContentRepoJson(meta, QUEST_CONTENT_REPO_LOCATION)
        val quest = questContentRepo.findById(TEST_ID)
        Assert.assertNotNull(quest)
        Assert.assertNotNull(quest?.pages)
        Assert.assertEquals(quest?.pages?.size, 3)

        Assert.assertNotNull(quest?.pages?.get(FIRST))
        Assert.assertNotNull(quest?.pages?.get(FIRST)?.choices)
        Assert.assertEquals(quest?.pages?.get(FIRST)?.choices?.size, 2)
    }
}