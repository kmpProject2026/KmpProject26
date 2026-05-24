package com.itis.kmpproj26.feature.words.ui.screen.add

import com.itis.kmpproj26.core.viewmodel.BaseViewModel
import com.itis.kmpproj26.feature.words.ui.screen.add.handler.AddWordBackEventHandler
import com.itis.kmpproj26.feature.words.ui.screen.add.handler.AddWordHideErrorDialogEventHandler
import com.itis.kmpproj26.feature.words.ui.screen.add.handler.AddWordSaveEventHandler
import com.itis.kmpproj26.feature.words.ui.screen.add.handler.AddWordSourceLanguageChangedEventHandler
import com.itis.kmpproj26.feature.words.ui.screen.add.handler.AddWordSpellingChangedEventHandler
import com.itis.kmpproj26.feature.words.ui.screen.add.handler.AddWordTargetLanguageChangedEventHandler
import com.itis.kmpproj26.feature.words.ui.screen.add.handler.AddWordTranslateEventHandler
import com.itis.kmpproj26.feature.words.ui.screen.add.handler.AddWordTranslationChangedEventHandler
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import com.itis.kmpproj26.feature.words.ui.screen.add.AddWordAction as Action
import com.itis.kmpproj26.feature.words.ui.screen.add.AddWordEvent as Event
import com.itis.kmpproj26.feature.words.ui.screen.add.AddWordState as State

class AddWordViewModel : BaseViewModel<State, Action, Event>(
    initState = State()
), KoinComponent {

    private val spellingChangedEventHandler: AddWordSpellingChangedEventHandler by inject()
    private val translationChangedEventHandler: AddWordTranslationChangedEventHandler by inject()
    private val sourceLanguageChangedEventHandler: AddWordSourceLanguageChangedEventHandler by inject()
    private val targetLanguageChangedEventHandler: AddWordTargetLanguageChangedEventHandler by inject()
    private val translateEventHandler: AddWordTranslateEventHandler by inject()
    private val saveEventHandler: AddWordSaveEventHandler by inject()
    private val backEventHandler: AddWordBackEventHandler by inject()
    private val hideErrorDialogEventHandler: AddWordHideErrorDialogEventHandler by inject()

    override fun obtainEvent(event: Event) {
        when (event) {
            is Event.HideErrorDialog -> with(hideErrorDialogEventHandler) { obtain(event) }
            is Event.OnBackClick -> with(backEventHandler) { obtain(event) }
            is Event.OnSaveClick -> with(saveEventHandler) { obtain(event) }
            is Event.OnSpellingChanged -> with(spellingChangedEventHandler) { obtain(event) }
            is Event.OnSourceLanguageChanged -> with(sourceLanguageChangedEventHandler) { obtain(event) }
            is Event.OnTargetLanguageChanged -> with(targetLanguageChangedEventHandler) { obtain(event) }
            is Event.OnTranslateClick -> with(translateEventHandler) { obtain(event) }
            is Event.OnTranslationChanged -> with(translationChangedEventHandler) { obtain(event) }
        }
    }
}
