package com.example.teescurrencyconverter.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.teescurrencyconverter.room.entity.History

@Database(
    entities = [History::class],
    version = 1,
    exportSchema = false
)
abstract class ActivityListDatabase:RoomDatabase() {
    public abstract fun recordsDao(): HistoryDao

    companion object {
        @Volatile
        var INSTANCE: ActivityListDatabase? = null
        fun getDatabase(context: Context): ActivityListDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ActivityListDatabase::class.java,
                    "history_db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
