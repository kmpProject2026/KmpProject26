package com.itis.kmpproj26.feature.weather.data.datasource

import com.itis.kmpproj26.feature.weather.data.datasource.remote.WeatherResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

internal class RemoteWeatherDataSource(
    private val httpClient: HttpClient,
) {

    suspend fun getWeather(query: String): WeatherResponse {
        return httpClient.get {
            parameter("q", query)
        }.body()
    }
}