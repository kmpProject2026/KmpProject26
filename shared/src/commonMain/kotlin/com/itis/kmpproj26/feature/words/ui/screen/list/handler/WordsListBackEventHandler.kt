package com.itis.kmpproj26.feature.words.ui.screen.list.handler

import com.itis.kmpproj26.core.viewmodel.BaseEventHandler
import com.itis.kmpproj26.feature.words.ui.screen.list.WordsListAction
import com.itis.kmpproj26.feature.words.ui.screen.list.WordsListEvent
import com.itis.kmpproj26.feature.words.ui.screen.list.WordsListViewModel

class WordsListBackEventHandler :
    BaseEventHandler<WordsListEvent.OnBackClick, WordsListViewModel>() {

    override fun WordsListViewModel.obtain(event: WordsListEvent.OnBackClick) {
        viewAction = WordsListAction.NavigateBack
    }
}
