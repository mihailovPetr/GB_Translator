package com.example.repository.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.repository.entity.room.HistoryEntity
import com.example.repository.entity.room.RoomTranslation
import com.example.repository.entity.room.RoomWord

@Database(entities = [HistoryEntity::class, RoomWord::class, RoomTranslation::class], version = 2, exportSchema = false)
abstract class HistoryDataBase : RoomDatabase() {

    abstract fun historyDao(): HistoryDao
}
