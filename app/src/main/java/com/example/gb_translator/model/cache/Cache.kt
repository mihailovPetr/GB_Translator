package com.example.gb_translator.model.cache

import com.example.gb_translator.model.entity.Word
import com.example.gb_translator.model.entity.room.RoomTranslation
import com.example.gb_translator.room.HistoryDao
import com.example.gb_translator.model.entity.room.HistoryEntity
import com.example.gb_translator.utils.toRoomTranslation
import com.example.gb_translator.utils.toRoomWord
import com.example.gb_translator.utils.toWord

class Cache(private val dao: HistoryDao) : ICache {
    override suspend fun getWords(word: String): List<Word> {
        return dao.getWordAdvanced(word).map { it.toWord() }
    }

    override suspend fun putWords(words: List<Word>) {
        val roomWords = words.map { it.toRoomWord() }
        val roomTranslations: MutableList<RoomTranslation> = mutableListOf()

        for (word in words) {
            word.translations?.map { it.toRoomTranslation(word.id) }?.let {
                roomTranslations.addAll(it)
            }
        }

        dao.insertWordsWithTranslations(roomWords, roomTranslations)
    }

    override suspend fun getHistory(): List<HistoryEntity> = dao.getAllHistory()

    override suspend fun putHistoryEntity(historyEntity: HistoryEntity) {
        dao.insert(historyEntity)
    }
}