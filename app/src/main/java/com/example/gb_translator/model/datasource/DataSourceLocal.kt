package com.example.gb_translator.model.datasource

import com.example.gb_translator.model.data.AppState

interface DataSourceLocal<T> : DataSource<T> {

    suspend fun saveToDB(appState: AppState)
}
