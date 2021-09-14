package com.example.repository.cache

import com.example.repository.entity.room.HistoryEntity
import com.example.repository.entity.room.RoomTranslation
import com.example.repository.room.HistoryDao

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