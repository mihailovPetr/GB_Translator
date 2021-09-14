package com.example.gb_translator.view.main

import com.example.model.entity.AppState
import com.example.repository.repository.IRepository
import com.example.gb_translator.viewModel.Interactor

class MainInteractor(private val repository: IRepository) : Interactor<AppState> {

    override suspend fun getData(word: String): AppState {
        return AppState.Success(repository.getWords(word))
    }
}