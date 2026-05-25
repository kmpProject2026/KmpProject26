package com.itis.kmpproj26.feature.words.domain.usecase

import com.itis.kmpproj26.core.util.result.Result
import com.itis.kmpproj26.feature.words.domain.model.Word

interface GetWordsUseCase {
    suspend operator fun invoke(): Result<List<Word>>
}
