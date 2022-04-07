package dev.rodosteam.questtime.quest.repo

import dev.rodosteam.questtime.quest.model.QuestContent

interface QuestContentRepo {
    fun findById(id: Int): QuestContent?
    fun findByName(name: String): QuestContent?
}