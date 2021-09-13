package com.example.gb_translator.model.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Word(
    val id: Int,
    val text: String?,
    val translations: List<Translation>?
) : Parcelable