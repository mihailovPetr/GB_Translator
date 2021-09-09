package com.example.gb_translator.model.repository

import com.example.gb_translator.model.data.AppState

interface RepositoryLocal<T> : Repository<T> {

    suspend fun saveToDB(appState: AppState)
}
