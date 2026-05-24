package com.itis.kmpproj26.feature.words.domain.usecase

import com.itis.kmpproj26.core.util.result.Result
import com.itis.kmpproj26.feature.words.domain.model.Word

interface AddWordUseCase {
    suspend operator fun invoke(
        spelling: String,
        translation: String,
        sourceLanguage: String = Word.DEFAULT_SOURCE_LANGUAGE,
        targetLanguage: String = Word.DEFAULT_TARGET_LANGUAGE,
    ): Result<Word>
}
