package com.itis.kmpproj26.feature.words.ui.screen.list.handler

import com.itis.kmpproj26.core.viewmodel.BaseEventHandler
import com.itis.kmpproj26.feature.words.ui.screen.list.WordsListEvent
import com.itis.kmpproj26.feature.words.ui.screen.list.WordsListViewModel

class WordsListHideErrorDialogEventHandler :
    BaseEventHandler<WordsListEvent.HideErrorDialog, WordsListViewModel>() {

    override fun WordsListViewModel.obtain(event: WordsListEvent.HideErrorDialog) {
        viewState = viewState.copy(errorDialog = null)
    }
}
