package com.example.gb_translator.model.repository

import com.example.gb_translator.model.entity.Word
import com.example.gb_translator.model.entity.room.HistoryEntity

interface IRepository {
    suspend fun getWords(word: String): List<Word>
    suspend fun getAllHistory(): List<HistoryEntity>
}