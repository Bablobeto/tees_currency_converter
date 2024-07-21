package com.example.teescurrencyconverter.room.repository

import com.example.teescurrencyconverter.room.HistoryDao
import com.example.teescurrencyconverter.room.entity.History
import kotlinx.coroutines.flow.Flow

class Repository (
    private val recordsDao: HistoryDao,
){

    val readAllRecords: Flow<List<History>> = recordsDao.readAllRecords()

    suspend fun addRecord(record: History){
        recordsDao.addRecord(record)
    }

    fun getRecords(uid: String): Flow<List<History>> {
        return recordsDao.getRecords(uid)
    }

    suspend fun truncate(){
        recordsDao.truncate()
    }

}