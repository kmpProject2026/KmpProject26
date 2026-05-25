package com.itis.kmpproj26.feature.words.ui.screen.cards.handler

import com.itis.kmpproj26.core.viewmodel.BaseEventHandler
import com.itis.kmpproj26.feature.words.ui.screen.cards.WordsCardsEvent
import com.itis.kmpproj26.feature.words.ui.screen.cards.WordsCardsViewModel

class WordsCardsRememberEventHandler :
    BaseEventHandler<WordsCardsEvent.OnRememberClick, WordsCardsViewModel>() {

    override fun WordsCardsViewModel.obtain(event: WordsCardsEvent.OnRememberClick) {
        val currentIndex = viewState.currentIndex
        val updatedWords = viewState.words.toMutableList()

        if (updatedWords.getOrNull(currentIndex) == null) return

        updatedWords.removeAt(currentIndex)

        val nextIndex = when {
            updatedWords.isEmpty() -> 0
            currentIndex > updatedWords.lastIndex -> 0
            else -> currentIndex
        }

        viewState = viewState.copy(
            words = updatedWords,
            currentIndex = nextIndex,
        )
    }
}
