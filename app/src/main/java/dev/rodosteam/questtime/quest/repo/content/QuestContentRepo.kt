package dev.rodosteam.questtime.quest.repo.content

import dev.rodosteam.questtime.quest.model.QuestContent

interface QuestContentRepo {
    fun findById(id: Int): QuestContent?
}