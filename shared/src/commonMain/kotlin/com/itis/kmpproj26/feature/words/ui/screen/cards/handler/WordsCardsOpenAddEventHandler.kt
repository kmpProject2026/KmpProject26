package com.itis.kmpproj26.feature.words.ui.screen.cards.handler

import com.itis.kmpproj26.core.viewmodel.BaseEventHandler
import com.itis.kmpproj26.feature.words.ui.screen.cards.WordsCardsAction
import com.itis.kmpproj26.feature.words.ui.screen.cards.WordsCardsEvent
import com.itis.kmpproj26.feature.words.ui.screen.cards.WordsCardsViewModel

class WordsCardsOpenAddEventHandler :
    BaseEventHandler<WordsCardsEvent.OnAddClick, WordsCardsViewModel>() {

    override fun WordsCardsViewModel.obtain(event: WordsCardsEvent.OnAddClick) {
        viewAction = WordsCardsAction.NavigateToAdd
    }
}
