package com.itis.kmpproj26.feature.weather.domain.repository

import com.itis.kmpproj26.feature.weather.domain.model.CityWeather

interface WeatherRepository {

    suspend fun getWeather(city: String): CityWeather
}