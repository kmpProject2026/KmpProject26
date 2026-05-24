package com.itis.kmpproj26.feature.words.ui.screen.cards

import com.itis.kmpproj26.core.viewmodel.BaseViewModel
import com.itis.kmpproj26.feature.words.ui.screen.cards.handler.WordsCardsHideErrorDialogEventHandler
import com.itis.kmpproj26.feature.words.ui.screen.cards.handler.WordsCardsInitEventHandler
import com.itis.kmpproj26.feature.words.ui.screen.cards.handler.WordsCardsOpenAddEventHandler
import com.itis.kmpproj26.feature.words.ui.screen.cards.handler.WordsCardsOpenListEventHandler
import com.itis.kmpproj26.feature.words.ui.screen.cards.handler.WordsCardsRememberEventHandler
import com.itis.kmpproj26.feature.words.ui.screen.cards.handler.WordsCardsRepeatEventHandler
import com.itis.kmpproj26.feature.words.ui.screen.cards.handler.WordsCardsSourceLanguageFilterChangedEventHandler
import com.itis.kmpproj26.feature.words.ui.screen.cards.handler.WordsCardsTargetLanguageFilterChangedEventHandler
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import com.itis.kmpproj26.feature.words.ui.screen.cards.WordsCardsAction as Action
import com.itis.kmpproj26.feature.words.ui.screen.cards.WordsCardsEvent as Event
import com.itis.kmpproj26.feature.words.ui.screen.cards.WordsCardsState as State

class WordsCardsViewModel : BaseViewModel<State, Action, Event>(
    initState = State()
), KoinComponent {

    private val initEventHandler: WordsCardsInitEventHandler by inject()
    private val repeatEventHandler: WordsCardsRepeatEventHandler by inject()
    private val rememberEventHandler: WordsCardsRememberEventHandler by inject()
    private val sourceLanguageFilterChangedEventHandler: WordsCardsSourceLanguageFilterChangedEventHandler by inject()
    private val targetLanguageFilterChangedEventHandler: WordsCardsTargetLanguageFilterChangedEventHandler by inject()
    private val openAddEventHandler: WordsCardsOpenAddEventHandler by inject()
    private val openListEventHandler: WordsCardsOpenListEventHandler by inject()
    private val hideErrorDialogEventHandler: WordsCardsHideErrorDialogEventHandler by inject()

    override fun obtainEvent(event: Event) {
        when (event) {
            is Event.HideErrorDialog -> with(hideErrorDialogEventHandler) { obtain(event) }
            is Event.Init -> with(initEventHandler) { obtain(event) }
            is Event.OnAddClick -> with(openAddEventHandler) { obtain(event) }
            is Event.OnListClick -> with(openListEventHandler) { obtain(event) }
            is Event.OnRememberClick -> with(rememberEventHandler) { obtain(event) }
            is Event.OnRepeatClick -> with(repeatEventHandler) { obtain(event) }
            is Event.OnSourceLanguageFilterChanged -> with(sourceLanguageFilterChangedEventHandler) { obtain(event) }
            is Event.OnTargetLanguageFilterChanged -> with(targetLanguageFilterChangedEventHandler) { obtain(event) }
        }
    }
}
