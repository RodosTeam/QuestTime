package dev.rodosteam.questtime.quest.repo.meta

import dev.rodosteam.questtime.quest.model.QuestMeta

interface QuestMetaRepo {
    fun findAll(): List<QuestMeta>
    fun findById(id: Int): QuestMeta?
    fun findByName(name: String): QuestMeta?
    fun add(item: QuestMeta): Boolean
    fun addAll(metas: Map<Int, QuestMeta>)
    fun remove(id: Int): QuestMeta?
}
