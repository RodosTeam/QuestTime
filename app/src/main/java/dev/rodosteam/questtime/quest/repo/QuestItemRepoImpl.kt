package dev.rodosteam.questtime.quest.repo

import dev.rodosteam.questtime.quest.model.QuestItem

class QuestItemRepoImpl : QuestItemRepo {
    private var quests = mutableMapOf<Int, QuestItem>()

    init {
        val questsInfo = QuestsInfo()
        repeat(10) {
            quests[it] = questsInfo.generateRandomQuest(it)
        }
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
