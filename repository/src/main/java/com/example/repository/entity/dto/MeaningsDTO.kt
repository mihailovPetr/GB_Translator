package com.example.repository.entity.dto

import com.google.gson.annotations.SerializedName

class MeaningsDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("translation") val translationDTO: TranslationDTO?,
    @SerializedName("imageUrl") val imageUrl: String?,
    @SerializedName("transcription") val transcription: String?
)
