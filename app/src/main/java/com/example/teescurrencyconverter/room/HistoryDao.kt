package com.example.teescurrencyconverter.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.teescurrencyconverter.room.entity.History
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRecord(records: History)

    @Query("SELECT * FROM history WHERE uid =:uid ORDER BY id DESC")
    fun getRecords(uid:String): Flow<List<History>>

    @Query("SELECT * FROM history LIMIT 1")
    fun readAllRecords(): Flow<List<History>>

    @Query("DELETE FROM history")
    suspend fun truncate()
}
