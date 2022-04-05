package dev.rodosteam.questtime.quest.repo

import dev.rodosteam.questtime.api.dto.QuestItemDto
import dev.rodosteam.questtime.quest.model.QuestItem

interface QuestItemRepo {
    fun findAll(): List<QuestItemDto>
    fun findById(id: Long): QuestItemDto
    fun findByName(name: String) : QuestItemDto
    fun add(item: QuestItem): Boolean
}

