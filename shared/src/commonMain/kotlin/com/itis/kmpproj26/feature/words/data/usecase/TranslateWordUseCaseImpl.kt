package com.itis.kmpproj26.feature.words.data.usecase

import com.itis.kmpproj26.core.util.result.Result
import com.itis.kmpproj26.feature.words.domain.repository.WordsRepository
import com.itis.kmpproj26.feature.words.domain.usecase.TranslateWordUseCase

class TranslateWordUseCaseImpl(
    private val wordsRepository: WordsRepository,
) : TranslateWordUseCase {

    override suspend operator fun invoke(
        spelling: String,
        sourceLanguage: String,
        targetLanguage: String,
    ): Result<String> {
        return wordsRepository.translateWord(
            spelling = spelling,
            sourceLanguage = sourceLanguage,
            targetLanguage = targetLanguage,
        )
    }
}
