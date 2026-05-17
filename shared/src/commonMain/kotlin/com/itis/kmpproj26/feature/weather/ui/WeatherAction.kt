package com.itis.kmpproj26.feature.weather.ui

sealed class WeatherAction {

    data object CloseScreen : WeatherAction()
    data class ShowMessage(val message: String) : WeatherAction()
}