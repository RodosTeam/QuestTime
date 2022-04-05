package dev.rodosteam.questtime.quest.model

data class QuestItem(
    val id: Int,
    val title: String,
    val description: String,
    val playCount: Int,
    val upVotes: Int,
    val downVotes: Int,
    val created: Long,
)