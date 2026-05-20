package com.itis.kmpproj26

import android.app.Application
import com.itis.kmpproj26.common.navigation.navigationModule
import com.itis.kmpproj26.config.initCommon
import org.koin.core.context.GlobalContext

class AppDelegate : Application() {

    override fun onCreate() {
        super.onCreate()
        initCommon()

        GlobalContext.get().loadModules(listOf(navigationModule))
    }
}