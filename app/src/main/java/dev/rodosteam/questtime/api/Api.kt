package dev.rodosteam.questtime.api

import dev.rodosteam.questtime.api.dto.QuestMetaDto

interface Api {

    fun fetchQuestItem(id: Long): QuestMetaDto
    fun fetchQuestContent(id: Long): QuestMetaDto


}