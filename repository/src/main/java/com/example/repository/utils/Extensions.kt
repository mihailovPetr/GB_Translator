package com.example.repository.utils

import com.example.gb_translator.model.entity.Translation
import com.example.gb_translator.model.entity.Word
import com.example.repository.entity.dto.MeaningsDTO
import com.example.repository.entity.dto.WordDTO
import com.example.repository.entity.room.RoomTranslation
import com.example.repository.entity.room.RoomWordWithTranslations

fun WordDTO.toWord() = Word(id, text, meaningsDTO?.map { it.toTranslation() })

fun MeaningsDTO.toTranslation() = Translation(id, translationDTO?.text, imageUrl, transcription)

fun Word.toRoomWord() = RoomWord(id, text)

fun Translation.toRoomTranslation(wordId: Int) =
    RoomTranslation(id, wordId, translation, imageUrl, transcription)

fun RoomWordWithTranslations.toWord() =
    Word(word.id, word.text, translations.map { it.toTranslation() })

fun RoomTranslation.toTranslation() = Translation(id, translation, imageUrl, transcription)

fun Word.getTranslationsString(): String{
    val translStringList = translations?.map { it.translation } ?: listOf()
    return TextUtils.join(", ", translStringList)
}