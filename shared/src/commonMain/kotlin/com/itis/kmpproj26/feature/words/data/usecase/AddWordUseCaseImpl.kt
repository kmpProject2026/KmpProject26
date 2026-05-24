package com.itis.kmpproj26.feature.words.data.usecase

import com.itis.kmpproj26.core.util.result.Result
import com.itis.kmpproj26.feature.words.domain.model.Word
import com.itis.kmpproj26.feature.words.domain.repository.WordsRepository
import com.itis.kmpproj26.feature.words.domain.usecase.AddWordUseCase

class AddWordUseCaseImpl(
    private val wordsRepository: WordsRepository,
) : AddWordUseCase {

    override suspend operator fun invoke(
        spelling: String,
        translation: String,
        sourceLanguage: String,
        targetLanguage: String,
    ): Result<Word> {
        return wordsRepository.addWord(
            spelling = spelling,
            translation = translation,
            sourceLanguage = sourceLanguage,
            targetLanguage = targetLanguage,
        )
    }
}
