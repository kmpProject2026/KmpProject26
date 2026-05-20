package com.itis.kmpproj26.feature.weather.data.usecase

import com.itis.kmpproj26.feature.weather.domain.model.CityWeather
import com.itis.kmpproj26.feature.weather.domain.repository.WeatherRepository
import com.itis.kmpproj26.feature.weather.domain.usecase.GetWeatherUseCase

internal class GetWeatherUseCaseImpl(
    private val weatherRepository: WeatherRepository
) : GetWeatherUseCase {

    override suspend fun invoke(city: String): CityWeather {
        return weatherRepository.getWeather(city)
    }
}