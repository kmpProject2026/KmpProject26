package com.itis.kmpproj26.feature

import com.itis.kmpproj26.feature.auth.di.authModule
import com.itis.kmpproj26.feature.profile.di.profileModule
import com.itis.kmpproj26.feature.weather.data.weatherModule
import org.koin.dsl.module

val featureModule = module {
    includes(
        weatherModule,
        authModule,
        profileModule,
    )
}
