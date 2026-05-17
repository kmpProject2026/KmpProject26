package com.itis.kmpproj26.feature.weather.data.datasource.remote

import com.itis.kmpproj26.feature.weather.domain.model.CityWeather
import com.itis.kmpproj26.feature.weather.domain.model.Day
import com.itis.kmpproj26.feature.weather.domain.model.Weather
import com.itis.kmpproj26.feature.weather.domain.model.Wind

fun WeatherResponse.mapToEntity(): CityWeather {
    return CityWeather(
        name = requireNotNull(name),
        lat = coord?.lat ?: 0.0,
        lon = coord?.lon ?: 0.0,
        temp = main?.temp ?: 0.0,
        feelTemp = main?.feelsLike ?: 0.0,
        humidity = main?.humidity ?: 0,
        pressure = main?.pressure ?: 0,
        cloudsPercent = clouds?.all ?: 0,
        day = requireNotNull(sys?.mapToDomain()),
        wind = requireNotNull(wind?.mapToDomain()),
        weather = requireNotNull(weather?.firstOrNull()?.mapToDomain()),
    )
}

private fun WeatherResponse.Wind.mapToDomain(): Wind = Wind(
    deg = deg ?: 0,
    speed = speed ?: 0.0,
)

private fun WeatherResponse.Sys.mapToDomain(): Day = Day(
    sunrise = sunrise ?: 0,
    sunset = sunset ?: 0,
)

private fun WeatherResponse.Weather.mapToDomain(): Weather = Weather(
    name = main.orEmpty(),
    desc = description.orEmpty(),
    imageUrl = icon?.mapImageUrl().orEmpty()
)

private fun String.mapImageUrl(): String = "https://openweathermap.org/img/w/$this.png"
