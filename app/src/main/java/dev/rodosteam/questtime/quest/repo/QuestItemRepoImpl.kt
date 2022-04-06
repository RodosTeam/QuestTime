package dev.rodosteam.questtime.quest.repo

import dev.rodosteam.questtime.quest.model.QuestItem

class QuestItemRepoImpl : QuestItemRepo {
    private val questsList = arrayListOf<QuestItem>()

    init {
        val questsInfo = QuestsInfo()
        questsList.add(questsInfo.generateRandomQuest(1))
        questsList.add(questsInfo.generateRandomQuest(2))
        questsList.add(questsInfo.generateRandomQuest(3))
        questsList.add(questsInfo.generateRandomQuest(4))
        questsList.add(questsInfo.generateRandomQuest(5))
    }

    override fun findAll(): List<QuestItem> {
        return questsList
    }

    override fun findById(id: Int): QuestItem? {
        for (quest in questsList) {
            if (quest.id == id) {
                return quest
            }
        }
        return null
    }

    override fun findByName(name: String): QuestItem? {
        for (quest in questsList) {
            if (quest.title.contains(name)) {
                return quest
            }
        }
        return null
    }

    override fun add(item: QuestItem): Boolean {
        return questsList.add(item)
    }
}