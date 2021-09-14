package com.example.repository.entity.room

import androidx.room.Embedded
import androidx.room.Relation

class RoomWordWithTranslations(
    @Embedded val word: RoomWord,
    @Relation(
        parentColumn = "id",
        entityColumn = "wordId"
    )
    val translations: List<RoomTranslation>
)