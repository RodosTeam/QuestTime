package dev.rodosteam.questtime.quest.model

data class QuestItem(
    val id: Int,
    val title: String,
    val description: String,
    val author: String,
    val downloads: Int,
    val favorites: Int,
    val created: Long,
)