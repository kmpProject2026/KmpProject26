package com.itis.kmpproj26.feature.words.ui.screen.list

import com.itis.kmpproj26.core.viewmodel.BaseViewModel
import com.itis.kmpproj26.feature.words.ui.screen.list.handler.WordsListBackEventHandler
import com.itis.kmpproj26.feature.words.ui.screen.list.handler.WordsListDeleteEventHandler
import com.itis.kmpproj26.feature.words.ui.screen.list.handler.WordsListHideErrorDialogEventHandler
import com.itis.kmpproj26.feature.words.ui.screen.list.handler.WordsListInitEventHandler
import com.itis.kmpproj26.feature.words.ui.screen.list.handler.WordsListOpenAddEventHandler
import com.itis.kmpproj26.feature.words.ui.screen.list.handler.WordsListSourceLanguageFilterChangedEventHandler
import com.itis.kmpproj26.feature.words.ui.screen.list.handler.WordsListTargetLanguageFilterChangedEventHandler
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import com.itis.kmpproj26.feature.words.ui.screen.list.WordsListAction as Action
import com.itis.kmpproj26.feature.words.ui.screen.list.WordsListEvent as Event
import com.itis.kmpproj26.feature.words.ui.screen.list.WordsListState as State

class WordsListViewModel : BaseViewModel<State, Action, Event>(
    initState = State()
), KoinComponent {

    private val initEventHandler: WordsListInitEventHandler by inject()
    private val backEventHandler: WordsListBackEventHandler by inject()
    private val openAddEventHandler: WordsListOpenAddEventHandler by inject()
    private val deleteEventHandler: WordsListDeleteEventHandler by inject()
    private val sourceLanguageFilterChangedEventHandler: WordsListSourceLanguageFilterChangedEventHandler by inject()
    private val targetLanguageFilterChangedEventHandler: WordsListTargetLanguageFilterChangedEventHandler by inject()
    private val hideErrorDialogEventHandler: WordsListHideErrorDialogEventHandler by inject()

    override fun obtainEvent(event: Event) {
        when (event) {
            is Event.HideErrorDialog -> with(hideErrorDialogEventHandler) { obtain(event) }
            is Event.Init -> with(initEventHandler) { obtain(event) }
            is Event.OnAddClick -> with(openAddEventHandler) { obtain(event) }
            is Event.OnBackClick -> with(backEventHandler) { obtain(event) }
            is Event.OnDeleteClick -> with(deleteEventHandler) { obtain(event) }
            is Event.OnSourceLanguageFilterChanged -> with(sourceLanguageFilterChangedEventHandler) { obtain(event) }
            is Event.OnTargetLanguageFilterChanged -> with(targetLanguageFilterChangedEventHandler) { obtain(event) }
        }
    }
}
