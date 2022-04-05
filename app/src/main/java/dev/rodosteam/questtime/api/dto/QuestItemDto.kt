package dev.rodosteam.questtime.api.dto

//TODO
data class QuestItemDto(
    val id: Long,
    val title: String,
    val text: String,
    val viewCount: Int,
    val upVotes: Int,
    val downVotes: Int,
    val created: Long
)