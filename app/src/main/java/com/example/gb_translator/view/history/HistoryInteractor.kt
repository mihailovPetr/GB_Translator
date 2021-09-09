package com.example.gb_translator.view.history

import com.example.gb_translator.model.data.AppState
import com.example.gb_translator.model.data.DataModel
import com.example.gb_translator.model.repository.RepositoryLocal


class HistoryInteractor(private val repositoryLocal: RepositoryLocal<List<DataModel>>) {

    suspend fun getData(): AppState {
        return AppState.Success(repositoryLocal.getData(""))
    }
}
