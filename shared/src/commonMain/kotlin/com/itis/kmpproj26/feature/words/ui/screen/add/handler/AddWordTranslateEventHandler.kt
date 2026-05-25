package com.itis.kmpproj26.feature.words.ui.screen.add.handler

import androidx.lifecycle.viewModelScope
import com.itis.kmpproj26.core.util.result.FailureReason
import com.itis.kmpproj26.core.util.result.Result
import com.itis.kmpproj26.core.viewmodel.BaseEventHandler
import com.itis.kmpproj26.feature.words.domain.usecase.TranslateWordUseCase
import com.itis.kmpproj26.feature.words.ui.screen.add.AddWordErrorDialog
import com.itis.kmpproj26.feature.words.ui.screen.add.AddWordEvent
import com.itis.kmpproj26.feature.words.ui.screen.add.AddWordInputError
import com.itis.kmpproj26.feature.words.ui.screen.add.AddWordViewModel
import kotlinx.coroutines.launch

class AddWordTranslateEventHandler(
    private val translateWordUseCase: TranslateWordUseCase,
) : BaseEventHandler<AddWordEvent.OnTranslateClick, AddWordViewModel>() {

    override fun AddWordViewModel.obtain(event: AddWordEvent.OnTranslateClick) {
        val spelling = viewState.spelling.trim()
        if (spelling.isEmpty()) {
            viewState = viewState.copy(spellingError = AddWordInputError.EMPTY)
            return
        }

        viewModelScope.launch {
            viewState = viewState.copy(isTranslating = true)
            val languagePair = viewState.selectedLanguagePair

            when (
                val result = translateWordUseCase(
                    spelling = spelling,
                    sourceLanguage = languagePair.sourceLanguage,
                    targetLanguage = languagePair.targetLanguage,
                )
            ) {
                is Result.Success -> {
                    viewState = viewState.copy(
                        translation = result.data,
                        isTranslating = false,
                        translationError = null,
                    )
                }

                is Result.Failure -> onFailure(result.reason)
            }
        }
    }

    private fun AddWordViewModel.onFailure(reason: FailureReason) {
        viewState = when (reason) {
            is FailureReason.Network -> {
                viewState.copy(
                    isTranslating = false,
                    errorDialog = AddWordErrorDialog.NETWORK,
                )
            }

            else -> {
                viewState.copy(
                    isTranslating = false,
                    errorDialog = AddWordErrorDialog.UNKNOWN,
                )
            }
        }
    }
}
