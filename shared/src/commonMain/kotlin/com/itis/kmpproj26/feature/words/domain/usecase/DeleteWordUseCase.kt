package com.itis.kmpproj26.feature.words.domain.usecase

import com.itis.kmpproj26.core.util.result.Result

interface DeleteWordUseCase {
    suspend operator fun invoke(wordId: Long): Result<Boolean>
}
