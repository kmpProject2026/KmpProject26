package com.itis.kmpproj26.feature.words.data.usecase

import com.itis.kmpproj26.core.util.result.Result
import com.itis.kmpproj26.feature.words.domain.model.Word
import com.itis.kmpproj26.feature.words.domain.repository.WordsRepository
import com.itis.kmpproj26.feature.words.domain.usecase.GetWordsUseCase

class GetWordsUseCaseImpl(
    private val wordsRepository: WordsRepository,
) : GetWordsUseCase {

    override suspend operator fun invoke(): Result<List<Word>> {
        return wordsRepository.getWords()
    }
}
