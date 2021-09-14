package com.example.repository.cache

import com.example.gb_translator.model.entity.Word
import com.example.gb_translator.model.entity.room.HistoryEntity
import com.example.repository.entity.room.HistoryEntity

interface ICache {
    suspend fun getWords(word: String): List<Word>
    suspend fun putWords(words: List<Word>)
    suspend fun getHistory(): List<HistoryEntity>
    suspend fun putHistoryEntity(historyEntity: HistoryEntity)
}