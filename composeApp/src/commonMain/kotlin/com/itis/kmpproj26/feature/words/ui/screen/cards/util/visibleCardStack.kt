package com.itis.kmpproj26.feature.words.ui.screen.cards.util

import com.itis.kmpproj26.feature.words.domain.model.Word
import kotlin.math.min

fun visibleCardStack(
    words: List<Word>,
    currentIndex: Int,
): List<Pair<Int, Word>> {
    if (words.isEmpty()) return emptyList()

    return (0 until min(3, words.size)).map { position ->
        val index = (currentIndex + position) % words.size
        position to words[index]
    }
}
