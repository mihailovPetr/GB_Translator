package com.example.gb_translator.model.datasource

import com.example.gb_translator.model.data.AppState
import com.example.gb_translator.model.data.DataModel
import com.example.gb_translator.room.HistoryDao
import com.example.gb_translator.room.HistoryEntity

class RoomDataBaseImpl(private val historyDao: HistoryDao) :
    DataSourceLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return historyDao.all().map {
            DataModel(it.word, null)
        }
    }

    override suspend fun saveToDB(appState: AppState) {
        if (appState !is AppState.Success || appState.data.isNullOrEmpty()) return

        val word = appState.data[0].text
        if (word.isNullOrEmpty()) return

        historyDao.insert(HistoryEntity(word, null, null, null))
    }
}