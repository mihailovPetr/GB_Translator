package com.example.gb_translator.model.datasource



interface DataSource<T> {
    suspend fun getData(word: String): T
}