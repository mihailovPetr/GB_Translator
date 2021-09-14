package com.example.repository.repository

import com.example.model.entity.Word
import com.example.repository.api.IDataSource
import com.example.repository.cache.ICache
import com.example.repository.entity.room.HistoryEntity
import com.example.repository.networkStatus.INetworkStatus
import com.example.repository.utils.toWord

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