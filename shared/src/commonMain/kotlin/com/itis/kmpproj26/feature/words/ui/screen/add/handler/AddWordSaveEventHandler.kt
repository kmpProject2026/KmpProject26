package com.itis.kmpproj26.feature.words.ui.screen.add.handler

import androidx.lifecycle.viewModelScope
import com.itis.kmpproj26.core.util.result.Result
import com.itis.kmpproj26.core.viewmodel.BaseEventHandler
import com.itis.kmpproj26.feature.words.domain.usecase.AddWordUseCase
import com.itis.kmpproj26.feature.words.ui.screen.add.AddWordAction
import com.itis.kmpproj26.feature.words.ui.screen.add.AddWordErrorDialog
import com.itis.kmpproj26.feature.words.ui.screen.add.AddWordEvent
import com.itis.kmpproj26.feature.words.ui.screen.add.AddWordInputError
import com.itis.kmpproj26.feature.words.ui.screen.add.AddWordViewModel
import kotlinx.coroutines.launch

class AddWordSaveEventHandler(
    private val addWordUseCase: AddWordUseCase,
) : BaseEventHandler<AddWordEvent.OnSaveClick, AddWordViewModel>() {

    override fun AddWordViewModel.obtain(event: AddWordEvent.OnSaveClick) {
        val spelling = viewState.spelling.trim()
        val translation = viewState.translation.trim()
        val spellingError = AddWordInputError.EMPTY.takeIf { spelling.isEmpty() }
        val translationError = AddWordInputError.EMPTY.takeIf { translation.isEmpty() }

        if (spellingError != null || translationError != null) {
            viewState = viewState.copy(
                spellingError = spellingError,
                translationError = translationError,
            )
            return
        }

        viewModelScope.launch {
            viewState = viewState.copy(isSaving = true)
            val languagePair = viewState.selectedLanguagePair

            when (
                addWordUseCase(
                    spelling = spelling,
                    translation = translation,
                    sourceLanguage = languagePair.sourceLanguage,
                    targetLanguage = languagePair.targetLanguage,
                )
            ) {
                is Result.Success -> {
                    viewState = viewState.copy(isSaving = false)
                    viewAction = AddWordAction.NavigateBack
                }

                is Result.Failure -> {
                    viewState = viewState.copy(
                        isSaving = false,
                        errorDialog = AddWordErrorDialog.UNKNOWN,
                    )
                }
            }
        }
    }
}
