package com.example.gb_translator.viewModel

interface Interactor<T> {
    suspend fun getData(word: String, fromRemoteSource: Boolean): T
}