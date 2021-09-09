package com.example.gb_translator.room

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = arrayOf("word"), unique = true)])
class HistoryEntity(
    @field:PrimaryKey
    val word: String,
    val description: String?,
    val imageUrl: String?,
    val transcription: String?
)
