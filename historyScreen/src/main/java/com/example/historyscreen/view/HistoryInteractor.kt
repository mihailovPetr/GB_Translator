package com.example.historyscreen.view

import com.example.model.entity.AppState
import com.example.repository.repository.IRepository


class HistoryInteractor(private val repository: IRepository) {

    suspend fun getHistory(): AppState {
        return AppState.Success(repository.getAllHistory())
    }
}
