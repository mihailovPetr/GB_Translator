package com.example.gb_translator.model.entity.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomWord(
    @PrimaryKey val id: Int,
    val text: String?
)