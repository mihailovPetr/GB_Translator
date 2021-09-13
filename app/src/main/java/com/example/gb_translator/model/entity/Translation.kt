package com.example.gb_translator.model.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Translation(
    val id: Int,
    val translation: String?,
    val imageUrl: String?,
    val transcription: String?
) : Parcelable