package dev.rodosteam.questtime.quest.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.rodosteam.questtime.quest.model.QuestMeta

@Entity(tableName = "quest_table")
data class Quest(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String,
    val description: String,
    val author: String,
    val downloads: Int,
    val favorites: Int,
    val created: Long,
    val isFavorite: Boolean,
    val iconUrl: String,
    val contentJson: String
)


fun getQuestFromMeta(meta: QuestMeta) : Quest {
    return Quest(
        meta.id,
        meta.title,
        meta.description,
        meta.author,
        meta.downloads,
        meta.favorites,
        meta.created,
        false,
        meta.iconUrl,
        meta.jsonContent
    )
}