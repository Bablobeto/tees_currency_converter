package com.example.teescurrencyconverter.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history")
data class History(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    val uid: String,
    val source: String,
    val target: String,
    val rate: Double,
    val amount: Double,
    val result: Double
)