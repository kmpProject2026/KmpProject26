package com.itis.kmpproj26.feature.profile.di

import com.itis.kmpproj26.feature.profile.ui.screen.handler.ProfileHideErrorDialogEventHandler
import com.itis.kmpproj26.feature.profile.ui.screen.handler.ProfileInitEventHandler
import com.itis.kmpproj26.feature.profile.ui.screen.handler.ProfileOnDeleteProfileCancelledEventHandler
import com.itis.kmpproj26.feature.profile.ui.screen.handler.ProfileOnDeleteProfileClickEventHandler
import com.itis.kmpproj26.feature.profile.ui.screen.handler.ProfileOnDeleteProfileConfirmedEventHandler
import com.itis.kmpproj26.feature.profile.ui.screen.handler.ProfileOnLogoutClickEventHandler
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val profileModule = module {
    factoryOf(::ProfileHideErrorDialogEventHandler)
    factoryOf(::ProfileInitEventHandler)
    factoryOf(::ProfileOnDeleteProfileClickEventHandler)
    factoryOf(::ProfileOnDeleteProfileConfirmedEventHandler)
    factoryOf(::ProfileOnDeleteProfileCancelledEventHandler)
    factoryOf(::ProfileOnLogoutClickEventHandler)
}
