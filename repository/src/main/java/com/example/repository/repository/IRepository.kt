package com.example.repository.repository

import com.example.model.entity.Word
import com.example.repository.entity.room.HistoryEntity

interface IRepository {
    suspend fun getWords(word: String): List<Word>
    suspend fun getAllHistory(): List<HistoryEntity>
}