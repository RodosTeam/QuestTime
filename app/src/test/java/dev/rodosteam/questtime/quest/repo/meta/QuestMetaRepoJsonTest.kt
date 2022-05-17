package dev.rodosteam.questtime.quest.repo.meta

import dev.rodosteam.questtime.quest.model.QuestMeta
import dev.rodosteam.questtime.utils.InternalStorage
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class QuestMetaRepoJsonTest {
    companion object {
        private const val TEST_FILES_DIR = "../test_files"
        private val TEST_META = QuestMeta(
            id = -1,
            title = "Test quest",
            description = "Test quest description",
            author = "Lev Saskov",
            downloads = 0,
            favorites = 0,
            created = 0L,
            iconUrl = "quests_library/images/test_icon.png",
            jsonContent = "quests_library/test_quest.json"
        )
    }

    @Test
    fun reading_isCorrect() {
        val questMetaRepo = QuestMetaRepoJson(InternalStorage(File(TEST_FILES_DIR)))
        val meta = questMetaRepo.findById(TEST_META.id)
        assertEquals(TEST_META, meta)
    }
}