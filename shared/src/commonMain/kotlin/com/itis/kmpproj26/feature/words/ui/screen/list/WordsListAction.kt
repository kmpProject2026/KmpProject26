package com.itis.kmpproj26.feature.words.ui.screen.list

sealed class WordsListAction {
    data object NavigateBack : WordsListAction()
    data object NavigateToAdd : WordsListAction()
}
