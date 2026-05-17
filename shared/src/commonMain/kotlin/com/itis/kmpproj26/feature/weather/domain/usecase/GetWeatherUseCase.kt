package com.itis.kmpproj26.feature.weather.domain.usecase

import com.itis.kmpproj26.feature.weather.domain.model.CityWeather

interface GetWeatherUseCase {

    suspend operator fun invoke(city: String): CityWeather
}
