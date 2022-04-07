package dev.rodosteam.questtime.quest.repo

import dev.rodosteam.questtime.quest.model.QuestItem

class QuestMetaRepoJson : QuestItemRepo {
    override fun findAll(): List<QuestItem> {
        TODO("Not yet implemented")
    }

    override fun findAllByName(name: String): List<QuestItem> {
        TODO("Not yet implemented")
    }

    override fun findById(id: Int): QuestItem? {
        TODO("Not yet implemented")
    }

    override fun findByName(name: String): QuestItem? {
        TODO("Not yet implemented")
    }

    override fun add(item: QuestItem): Boolean {
        TODO("Not yet implemented")
    }

    override fun remove(id: Int): QuestItem? {
        TODO("Not yet implemented")
    }
}