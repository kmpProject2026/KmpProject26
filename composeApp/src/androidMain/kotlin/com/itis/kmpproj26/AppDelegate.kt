package com.itis.kmpproj26

import android.app.Application
import com.itis.kmpproj26.config.initCommon

class AppDelegate : Application() {

    override fun onCreate() {
        super.onCreate()
        initCommon()
    }
}