package com.itis.kmpproj26.feature.words.ui.screen.list.handler

import com.itis.kmpproj26.core.viewmodel.BaseEventHandler
import com.itis.kmpproj26.feature.words.ui.screen.list.WordsListAction
import com.itis.kmpproj26.feature.words.ui.screen.list.WordsListEvent
import com.itis.kmpproj26.feature.words.ui.screen.list.WordsListViewModel

class WordsListOpenAddEventHandler :
    BaseEventHandler<WordsListEvent.OnAddClick, WordsListViewModel>() {

    override fun WordsListViewModel.obtain(event: WordsListEvent.OnAddClick) {
        viewAction = WordsListAction.NavigateToAdd
    }
}
