package dev.rodosteam.questtime.api.dto

import dev.rodosteam.questtime.quest.model.QuestMeta


fun getMeta(dto: QuestMetaDto): QuestMeta {
    return QuestMeta(
        dto.id.toInt(),
        dto.title,
        dto.text,
        dto.author,
        dto.downloads,
        dto.favorites,
        dto.created,
        dto.imageUrl,
        ""
    )
}