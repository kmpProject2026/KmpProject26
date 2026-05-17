package com.itis.kmpproj26.feature.weather.ui

sealed class WeatherEvent {

    data object OnCloseButtonClick : WeatherEvent()
    data object OnSearchClick : WeatherEvent()

    data class OnNameChange(val text: String) : WeatherEvent()
    data class OnPassChange(val pass: String) : WeatherEvent()

}
