package com.example.gb_translator.model.entity.dto

import com.google.gson.annotations.SerializedName

class WordDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("text") val text: String?,
    @SerializedName("meanings") val meaningsDTO: List<MeaningsDTO>?
)