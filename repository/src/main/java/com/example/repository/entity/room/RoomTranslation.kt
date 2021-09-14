package com.example.repository.entity.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = RoomWord::class,
        parentColumns = ["id"],
        childColumns = ["wordId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class RoomTranslation(
    @PrimaryKey val id: Int,
    val wordId: Int,
    val translation: String?,
    val imageUrl: String?,
    val transcription: String?
)