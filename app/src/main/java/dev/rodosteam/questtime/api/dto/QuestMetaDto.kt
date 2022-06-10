package dev.rodosteam.questtime.api.dto

data class QuestMetaDto(
    var id: Long = 0L,
    var title: String = "",
    var text: String = "",
    var author: String = "",
    var downloads: Int = 0,
    var favorites: Int = 0,
    var created: Long = 0L,
    var imageUrl: String = "",
    var jsonContent: String = ""
)