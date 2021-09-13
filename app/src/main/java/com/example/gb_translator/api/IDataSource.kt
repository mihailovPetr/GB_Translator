package com.example.gb_translator.api

import com.example.gb_translator.model.entity.dto.WordDTO
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface IDataSource {
    @GET("words/search")
    fun searchAsync(@Query("search") word: String): Deferred<List<WordDTO>>
}