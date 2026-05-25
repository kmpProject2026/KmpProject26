package com.itis.kmpproj26.feature.words.ui.screen.cards

sealed class WordsCardsAction {
    data object NavigateToAdd : WordsCardsAction()
    data object NavigateToList : WordsCardsAction()
}
