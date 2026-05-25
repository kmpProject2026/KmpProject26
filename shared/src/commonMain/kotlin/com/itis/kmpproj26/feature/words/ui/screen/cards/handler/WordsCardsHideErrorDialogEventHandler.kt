package com.itis.kmpproj26.feature.words.ui.screen.cards.handler

import com.itis.kmpproj26.core.viewmodel.BaseEventHandler
import com.itis.kmpproj26.feature.words.ui.screen.cards.WordsCardsEvent
import com.itis.kmpproj26.feature.words.ui.screen.cards.WordsCardsViewModel

class WordsCardsHideErrorDialogEventHandler :
    BaseEventHandler<WordsCardsEvent.HideErrorDialog, WordsCardsViewModel>() {

    override fun WordsCardsViewModel.obtain(event: WordsCardsEvent.HideErrorDialog) {
        viewState = viewState.copy(errorDialog = null)
    }
}
