package com.itis.kmpproj26.feature.words.ui.screen.cards.handler

import androidx.lifecycle.viewModelScope
import com.itis.kmpproj26.core.analytics.AnalyticsEvent
import com.itis.kmpproj26.core.analytics.AnalyticsService
import com.itis.kmpproj26.core.util.result.Result
import com.itis.kmpproj26.core.viewmodel.BaseEventHandler
import com.itis.kmpproj26.feature.words.domain.model.WordLanguagePair
import com.itis.kmpproj26.feature.words.domain.usecase.GetWordsUseCase
import com.itis.kmpproj26.feature.words.ui.screen.cards.WordsCardsErrorDialog
import com.itis.kmpproj26.feature.words.ui.screen.cards.WordsCardsEvent
import com.itis.kmpproj26.feature.words.ui.screen.cards.WordsCardsViewModel
import kotlinx.coroutines.launch

class WordsCardsInitEventHandler(
    private val getWordsUseCase: GetWordsUseCase,
    private val analyticsService: AnalyticsService,
) : BaseEventHandler<WordsCardsEvent.Init, WordsCardsViewModel>() {

    override fun WordsCardsViewModel.obtain(event: WordsCardsEvent.Init) {
        analyticsService.logEvent(AnalyticsEvent.LAUNCH_WORDS_CARDS)

        viewModelScope.launch {
            viewState = viewState.copy(isLoading = true)

            when (val result = getWordsUseCase()) {
                is Result.Success -> {
                    viewState = viewState.copy(
                        isLoading = false,
                        allWords = result.data,
                        words = result.data.filter { word ->
                            WordLanguagePair.matches(
                                word = word,
                                sourceLanguage = viewState.selectedSourceLanguageFilter,
                                targetLanguage = viewState.selectedTargetLanguageFilter,
                            )
                        },
                        currentIndex = 0,
                    )
                }

                is Result.Failure -> {
                    viewState = viewState.copy(
                        isLoading = false,
                        errorDialog = WordsCardsErrorDialog.UNKNOWN,
                    )
                }
            }
        }
    }
}
