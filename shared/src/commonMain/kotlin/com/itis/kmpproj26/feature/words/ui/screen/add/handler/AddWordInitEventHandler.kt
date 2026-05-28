package com.itis.kmpproj26.feature.words.ui.screen.add.handler

import com.itis.kmpproj26.core.analytics.AnalyticsEvent
import com.itis.kmpproj26.core.analytics.AnalyticsService
import com.itis.kmpproj26.core.viewmodel.BaseEventHandler
import com.itis.kmpproj26.feature.words.ui.screen.add.AddWordEvent
import com.itis.kmpproj26.feature.words.ui.screen.add.AddWordViewModel

class AddWordInitEventHandler(
    private val analyticsService: AnalyticsService,
) : BaseEventHandler<AddWordEvent.Init, AddWordViewModel>() {

    override fun AddWordViewModel.obtain(event: AddWordEvent.Init) {
        analyticsService.logEvent(AnalyticsEvent.LAUNCH_ADD_WORD)
    }
}
