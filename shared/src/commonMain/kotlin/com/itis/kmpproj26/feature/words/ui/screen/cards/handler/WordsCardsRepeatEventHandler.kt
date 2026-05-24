package com.itis.kmpproj26.feature.words.ui.screen.cards.handler

import com.itis.kmpproj26.core.viewmodel.BaseEventHandler
import com.itis.kmpproj26.feature.words.ui.screen.cards.WordsCardsEvent
import com.itis.kmpproj26.feature.words.ui.screen.cards.WordsCardsViewModel

class WordsCardsRepeatEventHandler :
    BaseEventHandler<WordsCardsEvent.OnRepeatClick, WordsCardsViewModel>() {

    override fun WordsCardsViewModel.obtain(event: WordsCardsEvent.OnRepeatClick) {
        val currentIndex = viewState.currentIndex
        val currentWord = viewState.words.getOrNull(currentIndex) ?: return
        val wordsWithoutCurrent = viewState.words.toMutableList().apply {
            removeAt(currentIndex)
        }

        val nextIndex = when {
            wordsWithoutCurrent.isEmpty() -> 0
            currentIndex > wordsWithoutCurrent.lastIndex -> 0
            else -> currentIndex
        }

        viewState = viewState.copy(
            words = wordsWithoutCurrent + currentWord,
            currentIndex = nextIndex,
        )
    }
}
