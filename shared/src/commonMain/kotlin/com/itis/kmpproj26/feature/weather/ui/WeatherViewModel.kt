package com.itis.kmpproj26.feature.weather.ui

import androidx.lifecycle.viewModelScope
import com.itis.kmpproj26.core.viewmodel.BaseViewModel
import com.itis.kmpproj26.feature.weather.domain.usecase.GetWeatherUseCase
import com.itis.kmpproj26.feature.weather.ui.WeatherEvent
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import com.itis.kmpproj26.feature.weather.ui.WeatherAction as Action
import com.itis.kmpproj26.feature.weather.ui.WeatherEvent as Event
import com.itis.kmpproj26.feature.weather.ui.WeatherViewState as State

class WeatherViewModel : BaseViewModel<State, Action, Event>(
    initState = State()
), KoinComponent {

    private val getWeatherUseCase: GetWeatherUseCase by inject()

    override fun obtainEvent(event: WeatherEvent) {
        when (event) {
            WeatherEvent.OnCloseButtonClick -> onCloseButtonClick()
            is WeatherEvent.OnNameChange -> onNameChange(event.text)
            is WeatherEvent.OnPassChange -> onPassChange(event.pass)
            WeatherEvent.OnSearchClick -> onSearchClick()
        }
    }

    private fun onCloseButtonClick() {

    }

    private fun onSearchClick() {
        viewModelScope.launch {
            try {
                viewState = viewState.copy(isLoading = true)
                val weather = getWeatherUseCase(viewState.userName)

                viewState = viewState.copy(
                    title = weather.name,
                    temp = "Feels like: ${weather.feelTemp}",
                    isLoading = false,
                )
            } catch (ex: CancellationException) {
                throw ex
            } catch (ex: Throwable) {
                // print
                viewState = viewState.copy(isLoading = false)
            }
        }
    }

    private fun onNameChange(text: String) {
        viewState = viewState.copy(
            userName = text,
            errorUserName = text.isValidateText()
        )
    }

    private fun String.isValidateText(): String? {
        return when {
            isEmpty() -> "ASHIBKA"
            else -> null
        }
    }

    private fun onPassChange(text: String) {

    }
}