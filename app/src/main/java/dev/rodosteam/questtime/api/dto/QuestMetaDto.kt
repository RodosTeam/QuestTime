package dev.rodosteam.questtime.api.dto

data class QuestMetaDto(
    val id: Long,
    val title: String,
    val text: String,
    val author: String,
    val downloads: Int,
    val favorites: Int,
    val created: Long,
    val imageUrl: String
)