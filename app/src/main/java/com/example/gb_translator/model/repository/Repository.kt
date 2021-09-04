package com.example.gb_translator.model.repository

interface Repository<T> {
    suspend fun getData(word: String): T
}