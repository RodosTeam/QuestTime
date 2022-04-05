package dev.rodosteam.questtime.api

import dev.rodosteam.questtime.api.dto.QuestItemDto

interface Api {

    fun fetchQuestItem(id: Long): QuestItemDto
    fun fetchQuestContent(id: Long): QuestItemDto


}