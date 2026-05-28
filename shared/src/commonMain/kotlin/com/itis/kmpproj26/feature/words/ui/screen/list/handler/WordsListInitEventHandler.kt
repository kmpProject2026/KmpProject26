package com.itis.kmpproj26.feature.words.ui.screen.list.handler

import androidx.lifecycle.viewModelScope
import com.itis.kmpproj26.core.analytics.AnalyticsEvent
import com.itis.kmpproj26.core.analytics.AnalyticsService
import com.itis.kmpproj26.core.util.result.Result
import com.itis.kmpproj26.core.viewmodel.BaseEventHandler
import com.itis.kmpproj26.feature.words.domain.usecase.GetWordsUseCase
import com.itis.kmpproj26.feature.words.ui.screen.list.WordsListErrorDialog
import com.itis.kmpproj26.feature.words.ui.screen.list.WordsListEvent
import com.itis.kmpproj26.feature.words.ui.screen.list.WordsListViewModel
import kotlinx.coroutines.launch

class WordsListInitEventHandler(
    private val getWordsUseCase: GetWordsUseCase,
    private val analyticsService: AnalyticsService,
) : BaseEventHandler<WordsListEvent.Init, WordsListViewModel>() {

    override fun WordsListViewModel.obtain(event: WordsListEvent.Init) {
        analyticsService.logEvent(AnalyticsEvent.LAUNCH_WORDS_LIST)

        viewModelScope.launch {
            viewState = viewState.copy(isLoading = true)

            when (val result = getWordsUseCase()) {
                is Result.Success -> {
                    viewState = viewState.copy(
                        isLoading = false,
                        allWords = result.data,
                    )
                }

                is Result.Failure -> {
                    viewState = viewState.copy(
                        isLoading = false,
                        errorDialog = WordsListErrorDialog.UNKNOWN,
                    )
                }
            }
        }
    }
}
