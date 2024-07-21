package com.example.teescurrencyconverter.room.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.teescurrencyconverter.room.ActivityListDatabase
import com.example.teescurrencyconverter.room.entity.History
import com.example.teescurrencyconverter.room.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class RecordsViewModel(
    application: Application
): AndroidViewModel(application) {

    var readAllData: Flow<List<History>>
    private val repository: Repository

    init {
        val recordsDao = ActivityListDatabase.getDatabase(application).recordsDao()
        repository = Repository(recordsDao)
        readAllData = repository.readAllRecords
    }

    fun addRecord(record: History){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addRecord(record)
        }
    }

    fun getAllRecords(uid : String) : Flow<List<History>>{
        // Trigger the fetch of all items
        return repository.getRecords(uid)
    }

    fun truncate() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.truncate()
        }
    }

}