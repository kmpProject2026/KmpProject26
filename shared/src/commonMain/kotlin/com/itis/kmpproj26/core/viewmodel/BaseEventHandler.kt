package com.itis.kmpproj26.core.viewmodel

abstract class BaseEventHandler<Event, ViewModel : BaseViewModel<*, *, in Event>> {
    abstract fun ViewModel.obtain(intent: Event)
}
