package dev.rodosteam.questtime.quest.repo.meta

import dev.rodosteam.questtime.quest.model.QuestMeta

interface QuestMetaRepo {
    fun findAll(): List<QuestMeta>
    fun findAllByName(name: String): List<QuestMeta>
    fun findById(id: Int): QuestMeta?
    fun findByName(name: String): QuestMeta?
    fun add(item: QuestMeta): Boolean
    fun remove(id: Int): QuestMeta?
    fun addAll(elements: Iterable<QuestMeta>)
}
