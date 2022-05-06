package dev.rodosteam.questtime.quest.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query

@Dao
interface QuestDao {

    @Insert(onConflict = IGNORE)
    suspend fun addQuest(quest: Quest)

    @Query("SELECT * FROM quest_table")
    fun readAllData(): List<Quest>

    @Query("SELECT * FROM quest_table WHERE id=:id")
    suspend fun findById(id: Int) : Quest?

    @Query("DELETE FROM quest_table WHERE id=:id")
    suspend fun removeById(id: Int)

    @Query("DELETE FROM quest_table")
    suspend fun removeAll()

}