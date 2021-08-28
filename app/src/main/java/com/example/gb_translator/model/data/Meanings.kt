package com.example.gb_translator.model.data

import com.google.gson.annotations.SerializedName

class Meanings(
    @SerializedName("translation") val translation: Translation?,
    @SerializedName("imageUrl") val imaggeUrl: String?,
    @SerializedName("transcription") val transcription: String?
)
