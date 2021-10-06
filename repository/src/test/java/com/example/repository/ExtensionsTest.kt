package com.example.repository

import com.example.model.entity.Word
import com.example.repository.entity.dto.MeaningsDTO
import com.example.repository.entity.dto.TranslationDTO
import com.example.repository.entity.dto.WordDTO
import com.example.repository.utils.getEvenArray
import com.example.repository.utils.getEvenArray2
import com.example.repository.utils.toWord
import org.junit.Assert
import org.junit.Test

class ExtensionsTest {

    @Test
    fun extensions_Test1() {
        val wordDTO = WordDTO(3, "word", null)
        val word = Word(3, "word", null)
        Assert.assertEquals(wordDTO.toWord(), word)
    }

    @Test
    fun extensions_Test2() {
        val wordDTO = WordDTO(3, "word,", null)
        val word = Word(3, null, null)
        Assert.assertNotEquals(wordDTO.toWord(), word)
    }

    @Test
    fun extensions_Test3() {
        val array1 = arrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
        val array2 = arrayOf(0, 2, 4, 6, 8)
        Assert.assertArrayEquals(array1.getEvenArray(), array2)
    }

    @Test
    fun extensions_Test4() {
        val wordDTO = WordDTO(3, "word", null)
        Assert.assertNull(wordDTO.toWord().translations)
    }

    @Test
    fun extensions_Test5() {
        val wordDTO = WordDTO(3, "word", listOf(MeaningsDTO(4, TranslationDTO("transl"), null, null)))
        Assert.assertNotNull(wordDTO.toWord().translations)
    }

    @Test
    fun extensions_Test6() {
        val array = arrayOf(0, 2, 4, 6, 8)
        Assert.assertSame(array.getEvenArray2(), array)
    }
}