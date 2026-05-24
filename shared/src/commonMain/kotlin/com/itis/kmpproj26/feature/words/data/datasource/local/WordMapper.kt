package com.itis.kmpproj26.feature.words.data.datasource.local

import com.itis.kmpproj26.Words
import com.itis.kmpproj26.feature.words.domain.model.Word

fun Words.mapToDomain(): Word {
    return Word(
        id = id,
        spelling = spelling,
        translation = translation,
        sourceLanguage = source_language,
        targetLanguage = target_language,
        createdAtMillis = created_at,
    )
}
