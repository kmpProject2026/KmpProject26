package com.itis.kmpproj26.feature.weather.domain.model

data class CityWeather(
    val name: String,
    val lat: Double,
    val lon: Double,
    val temp: Double,
    val feelTemp: Double,
    val humidity: Int,
    val pressure: Int,
    val cloudsPercent: Int,
    val wind: Wind,
    val day: Day,
    val weather: Weather,
)

data class Weather(
    val name: String,
    val desc: String,
    val imageUrl: String,
)

data class Wind(
    val speed: Double,
    val deg: Int,
)

data class Day(
    val sunrise: Long,
    val sunset: Long,
)
