package dev.rodosteam.questtime.quest.repo.content

import dev.rodosteam.questtime.App
import dev.rodosteam.questtime.quest.model.QuestContent
import dev.rodosteam.questtime.quest.model.QuestMeta
import dev.rodosteam.questtime.quest.repo.meta.QuestMetaRepoBase
import org.junit.Assert
import org.junit.Test

class QuestContentRepoJsonTest {
    companion object {
        const val TEST_ID = -1
        val FIRST = QuestContent.Page.Id(1)
    }

    @Test
    fun reading_isCorrect() {
        val questContentRepo = QuestContentRepoJson(TODO("access resources from tests"))
        val quest = questContentRepo.findById(TEST_ID)
        Assert.assertNotNull(quest)
        Assert.assertNotNull(quest?.pages)
        Assert.assertEquals(quest?.pages?.size, 3)

        Assert.assertNotNull(quest?.pages?.get(FIRST))
        Assert.assertNotNull(quest?.pages?.get(FIRST)?.choices)
        Assert.assertEquals(quest?.pages?.get(FIRST)?.choices?.size, 2)
    }
}