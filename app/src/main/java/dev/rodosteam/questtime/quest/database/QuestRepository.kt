package dev.rodosteam.questtime.quest.database

class QuestRepository(private val questDao: QuestDao) {

    var lastLoaded: MutableMap<Int, Quest> = emptyMap<Int, Quest>().toMutableMap()

    fun readAllData(): List<Quest> {
        lastLoaded.clear()
        val data = questDao.readAllData()
        for (quest in data) {
            lastLoaded[quest.id] = quest
        }
        return data
    }

    suspend fun addQuest(quest: Quest) {
        questDao.addQuest(quest)
    }

    suspend fun removeQuest(id: Int) {
        questDao.removeById(id)
    }

    suspend fun removeAll() {
        questDao.removeAll()
    }

}