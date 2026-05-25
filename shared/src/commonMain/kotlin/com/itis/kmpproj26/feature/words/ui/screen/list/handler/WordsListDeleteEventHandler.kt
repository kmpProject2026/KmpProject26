package com.itis.kmpproj26.feature.words.ui.screen.list.handler

import androidx.lifecycle.viewModelScope
import com.itis.kmpproj26.core.util.result.Result
import com.itis.kmpproj26.core.viewmodel.BaseEventHandler
import com.itis.kmpproj26.feature.words.domain.usecase.DeleteWordUseCase
import com.itis.kmpproj26.feature.words.ui.screen.list.WordsListErrorDialog
import com.itis.kmpproj26.feature.words.ui.screen.list.WordsListEvent
import com.itis.kmpproj26.feature.words.ui.screen.list.WordsListViewModel
import kotlinx.coroutines.launch

class WordsListDeleteEventHandler(
    private val deleteWordUseCase: DeleteWordUseCase,
) : BaseEventHandler<WordsListEvent.OnDeleteClick, WordsListViewModel>() {

    override fun WordsListViewModel.obtain(event: WordsListEvent.OnDeleteClick) {
        viewModelScope.launch {
            when (deleteWordUseCase(event.wordId)) {
                is Result.Success -> {
                    viewState = viewState.copy(
                        allWords = viewState.allWords.filterNot { it.id == event.wordId }
                    )
                }

                is Result.Failure -> {
                    viewState = viewState.copy(errorDialog = WordsListErrorDialog.UNKNOWN)
                }
            }
        }
    }
}
