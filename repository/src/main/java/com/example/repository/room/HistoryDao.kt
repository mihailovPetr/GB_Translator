package com.example.repository.room

import androidx.room.*
import com.example.repository.entity.room.HistoryEntity
import com.example.repository.entity.room.RoomTranslation
import com.example.repository.entity.room.RoomWord
import com.example.repository.entity.room.RoomWordWithTranslations

@Dao
abstract class HistoryDao {

    @Transaction
    @Query("SELECT * FROM RoomWord WHERE id = :id LIMIT 1")
    abstract suspend fun getWord(id: Int): List<RoomWordWithTranslations>

    @Transaction
    @Query("SELECT * FROM RoomWord WHERE text like '%' || :word || '%'")
    abstract suspend fun getWord(word: String): List<RoomWordWithTranslations>

    @Transaction
    @Query("SELECT RoomWord.* FROM RoomWord INNER JOIN RoomTranslation ON RoomWord.id = RoomTranslation.wordId WHERE RoomWord.text like '%' || :word || '%' OR RoomTranslation.translation like '%' || :word || '%'")
    abstract suspend fun getWordAdvanced(word: String): List<RoomWordWithTranslations>

    @Transaction
    open suspend fun insertWordsWithTranslations(words: List<RoomWord>, translations: List<RoomTranslation>){
        insertWords(words)
        insertTranslations(translations)
    }

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insertWords(words: List<RoomWord>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insertTranslations(translations: List<RoomTranslation>)

    @Query("SELECT * FROM HistoryEntity")
    abstract suspend fun getAllHistory(): List<HistoryEntity>

    @Query("SELECT * FROM HistoryEntity WHERE word LIKE :word")
    abstract suspend fun getDataByWord(word: String): HistoryEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insert(entity: HistoryEntity)

    @Delete
    abstract suspend fun delete(entity: HistoryEntity)
}
