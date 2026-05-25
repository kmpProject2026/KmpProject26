package com.itis.kmpproj26.feature.words.ui.screen.add.handler

import com.itis.kmpproj26.core.viewmodel.BaseEventHandler
import com.itis.kmpproj26.feature.words.ui.screen.add.AddWordEvent
import com.itis.kmpproj26.feature.words.ui.screen.add.AddWordViewModel

class AddWordHideErrorDialogEventHandler :
    BaseEventHandler<AddWordEvent.HideErrorDialog, AddWordViewModel>() {

    override fun AddWordViewModel.obtain(event: AddWordEvent.HideErrorDialog) {
        viewState = viewState.copy(errorDialog = null)
    }
}
