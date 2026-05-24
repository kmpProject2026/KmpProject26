package com.itis.kmpproj26.feature.words.ui.screen.add.handler

import com.itis.kmpproj26.core.viewmodel.BaseEventHandler
import com.itis.kmpproj26.feature.words.ui.screen.add.AddWordAction
import com.itis.kmpproj26.feature.words.ui.screen.add.AddWordEvent
import com.itis.kmpproj26.feature.words.ui.screen.add.AddWordViewModel

class AddWordBackEventHandler :
    BaseEventHandler<AddWordEvent.OnBackClick, AddWordViewModel>() {

    override fun AddWordViewModel.obtain(event: AddWordEvent.OnBackClick) {
        viewAction = AddWordAction.NavigateBack
    }
}
