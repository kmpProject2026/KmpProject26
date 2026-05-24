package com.itis.kmpproj26.feature.words.data.usecase

import com.itis.kmpproj26.core.util.result.Result
import com.itis.kmpproj26.feature.words.domain.repository.WordsRepository
import com.itis.kmpproj26.feature.words.domain.usecase.DeleteWordUseCase

class DeleteWordUseCaseImpl(
    private val wordsRepository: WordsRepository,
) : DeleteWordUseCase {

    override suspend operator fun invoke(wordId: Long): Result<Boolean> {
        return wordsRepository.deleteWord(wordId)
    }
}
