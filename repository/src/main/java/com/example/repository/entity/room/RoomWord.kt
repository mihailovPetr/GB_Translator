package com.example.repository.entity.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomWord(
    @PrimaryKey val id: Int,
    val text: String?
)