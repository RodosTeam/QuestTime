package dev.rodosteam.questtime.quest.repo.meta

import dev.rodosteam.questtime.quest.model.QuestMeta

open class QuestMetaRepoBase : QuestMetaRepo {
    protected val quests = mutableMapOf<Int, QuestMeta>()

    override fun findAll(): List<QuestMeta> {
        return quests.values.toList()
    }

    override fun findById(id: Int): QuestMeta? {
        return quests[id]
    }

    override fun findByName(name: String): QuestMeta? {
        for (quest in quests) {
            if (quest.value.title.contains(name)) {
                return quest.value
            }
        }
        return null
    }

    override fun findAllByName(name: String): List<QuestMeta> {
        name.lowercase()
        return quests.values.filter { it.title.lowercase().contains(name) }
    }

    override fun add(item: QuestMeta): Boolean {
        return quests.put(item.id, item) == null
    }

    fun addAll(metas: Map<Int, QuestMeta>) {
        quests.putAll(metas)
    }

    override fun remove(id: Int): QuestMeta? {
        return quests.remove(id)
    }
}