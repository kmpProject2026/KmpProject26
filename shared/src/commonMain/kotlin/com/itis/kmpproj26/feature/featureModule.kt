package com.itis.kmpproj26.feature

import com.itis.kmpproj26.feature.auth.di.authModule
import com.itis.kmpproj26.feature.weather.data.weatherModule
import com.itis.kmpproj26.feature.weather.ui.WeatherViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val featureModule = module {
    includes(
        weatherModule,
        authModule,
    )
//    viewModel {
//        WeatherViewModel()
//    }
}
