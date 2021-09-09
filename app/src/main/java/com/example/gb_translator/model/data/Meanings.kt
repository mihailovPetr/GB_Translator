package com.example.gb_translator.model.data

import com.google.gson.annotations.SerializedName

class Meanings(
    @SerializedName("translation") val translation: Translation?,
    @SerializedName("imageUrl") val imageUrl: String?,
    @SerializedName("transcription") val transcription: String?
)
