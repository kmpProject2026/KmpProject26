package com.itis.kmpproj26.feature.auth.ui.screen.login.handler

import com.itis.kmpproj26.core.analytics.AnalyticsEvent
import com.itis.kmpproj26.core.analytics.AnalyticsService
import com.itis.kmpproj26.core.viewmodel.BaseEventHandler
import com.itis.kmpproj26.feature.auth.ui.screen.login.LoginEvent
import com.itis.kmpproj26.feature.auth.ui.screen.login.LoginViewModel

class LoginInitEventHandler(
    private val analyticsService: AnalyticsService,
) :
    BaseEventHandler<LoginEvent.Init, LoginViewModel>() {

    override fun LoginViewModel.obtain(event: LoginEvent.Init) {
        analyticsService.logEvent(AnalyticsEvent.LAUNCH_LOGIN)
    }
}
