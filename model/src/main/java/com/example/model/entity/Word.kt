package com.example.model.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Word(
    val id: Int,
    val text: String?,
    val translations: List<Translation>?
) : Parcelable