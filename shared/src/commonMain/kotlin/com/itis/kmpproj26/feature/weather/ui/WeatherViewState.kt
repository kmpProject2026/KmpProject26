package com.itis.kmpproj26.feature.weather.ui

data class WeatherViewState(
    val title: String = "",
    val isLoading: Boolean = false,
    val temp: String = "",
    val cityName: String = "",

    val userName: String = "",
    val errorUserName: String? = null,
)