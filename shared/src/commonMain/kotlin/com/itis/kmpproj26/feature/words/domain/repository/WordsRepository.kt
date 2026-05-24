package com.itis.kmpproj26.feature.words.domain.repository

import com.itis.kmpproj26.core.util.result.Result
import com.itis.kmpproj26.feature.words.domain.model.Word

interface WordsRepository {

    suspend fun getWords(): Result<List<Word>>

    suspend fun translateWord(
        spelling: String,
        sourceLanguage: String,
        targetLanguage: String,
    ): Result<String>

    suspend fun addWord(
        spelling: String,
        translation: String,
        sourceLanguage: String,
        targetLanguage: String,
    ): Result<Word>

    suspend fun deleteWord(id: Long): Result<Boolean>
}
