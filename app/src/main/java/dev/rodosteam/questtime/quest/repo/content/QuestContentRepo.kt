package dev.rodosteam.questtime.quest.repo.content

import dev.rodosteam.questtime.quest.model.QuestContent

interface QuestContentRepo {
    /**
     * Find quest content by quest id.
     */
    fun findById(id: Int): QuestContent?
}