package com.example.gb_translator.view.history

import com.example.gb_translator.model.entity.AppState
import com.example.gb_translator.model.repository.IRepository
import com.example.gb_translator.model.repository.Repository


class HistoryInteractor(private val repository: IRepository) {

    suspend fun getHistory(): AppState {
        return AppState.Success(repository.getAllHistory())
    }
}
