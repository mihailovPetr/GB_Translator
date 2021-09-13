package com.example.gb_translator.model.repository

import com.example.gb_translator.api.IDataSource
import com.example.gb_translator.model.cache.ICache
import com.example.gb_translator.model.entity.Word
import com.example.gb_translator.networkStatus.INetworkStatus
import com.example.gb_translator.model.entity.room.HistoryEntity
import com.example.gb_translator.utils.toWord

class Repository(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val cache: ICache
) : IRepository {

    override suspend fun getWords(word: String): List<Word> {
        cache.putHistoryEntity(HistoryEntity(word))

        val result: List<Word>
        if (networkStatus.isOnline()) {
            result = api.searchAsync(word).await().map { it.toWord() }
            cache.putWords(result)
        } else {
            result = cache.getWords(word)
        }

        return result
    }

    override suspend fun getAllHistory(): List<HistoryEntity> = cache.getHistory()

}