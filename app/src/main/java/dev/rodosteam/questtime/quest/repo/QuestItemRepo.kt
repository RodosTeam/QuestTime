package dev.rodosteam.questtime.quest.repo
// TODO: rename to QuestMetaInfo
import dev.rodosteam.questtime.quest.model.QuestItem

interface QuestItemRepo {
    fun findAll(): List<QuestItem>
    fun findById(id: Int): QuestItem?
    fun findByName(name: String): QuestItem?
    fun add(item: QuestItem): Boolean
}
