package dev.rodosteam.questtime.quest.repo

import dev.rodosteam.questtime.quest.model.QuestItem

class QuestItemRepoImpl : QuestItemRepo {
    private var quests = mutableMapOf<Int, QuestItem>()

    init {
        val questsInfo = QuestsInfo()
        quests[1] = questsInfo.generateRandomQuest(1)
        quests[2] = questsInfo.generateRandomQuest(2)
        quests[3] = questsInfo.generateRandomQuest(3)
        quests[4] = questsInfo.generateRandomQuest(4)
        quests[5] = questsInfo.generateRandomQuest(5)
    }

    override fun findAll(): List<QuestItem> {
        return quests.values.toList()
    }

    override fun findById(id: Int): QuestItem? {
        return quests[id]
    }

    override fun findByName(name: String): QuestItem? {
        for (quest in quests) {
            if (quest.value.title.contains(name)) {
                return quest.value
            }
        }
        return null
    }

    override fun add(item: QuestItem): Boolean {
        return quests.put(item.id, item) == null
    }
}