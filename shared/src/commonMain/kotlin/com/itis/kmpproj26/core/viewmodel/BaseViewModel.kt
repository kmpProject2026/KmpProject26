package com.itis.kmpproj26.core.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

abstract class BaseViewModel<State : Any, Action, Event>(
    initState: State
): ViewModel() {

    private val _viewState = MutableStateFlow(initState)
    private val _viewAction = MutableSharedFlow<Action>(
        replay = 0,
        extraBufferCapacity = 1,
    )

    var viewState: State
        get() = _viewState.value
        set(value) {
            _viewState.value = value
        }

    var viewAction: Action
        get() = error("Get Action disable")
        set(value) {
            _viewAction.tryEmit(value)
        }

    val viewStates: StateFlow<State>
        get() = _viewState.asStateFlow()

    val viewActions: SharedFlow<Action>
        get() = _viewAction.asSharedFlow()

    abstract fun obtainEvent(event: Event)
}
