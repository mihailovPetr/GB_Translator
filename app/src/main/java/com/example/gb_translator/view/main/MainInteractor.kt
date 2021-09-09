package com.example.gb_translator.view.main

import com.example.gb_translator.model.data.AppState
import com.example.gb_translator.model.data.DataModel
import com.example.gb_translator.model.repository.Repository
import com.example.gb_translator.model.repository.RepositoryLocal
import com.example.gb_translator.viewModel.Interactor

class MainInteractor(
    private val repositoryRemote: Repository<List<DataModel>>,
    private val repositoryLocal: RepositoryLocal<List<DataModel>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        val appState: AppState
        if (fromRemoteSource) {
            appState = AppState.Success(repositoryRemote.getData(word))
            repositoryLocal.saveToDB(appState)
        } else {
            appState = AppState.Success(repositoryLocal.getData(word))
        }
        return appState
    }
}