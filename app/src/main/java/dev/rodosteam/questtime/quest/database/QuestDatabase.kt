package dev.rodosteam.questtime.quest.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Quest::class], version = 1, exportSchema = false)
abstract class QuestDatabase : RoomDatabase() {

    abstract fun questDao(): QuestDao

    companion object {
        @Volatile
        private var INSTANCE: QuestDatabase? = null

        fun getDataBase(context: Context): QuestDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    QuestDatabase::class.java,
                    "quest_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}