package com.itis.kmpproj26.feature.weather

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.retain.retain
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.itis.kmpproj26.feature.weather.ui.WeatherEvent
import com.itis.kmpproj26.feature.weather.ui.WeatherViewModel
import com.itis.kmpproj26.feature.weather.ui.WeatherViewState

@Composable
fun WeatherScreen() {

    val viewModel = retain { WeatherViewModel() }
    val state by viewModel.viewStates.collectAsStateWithLifecycle()

    WeatherView(
        state = state,
        eventConsumer = {
            viewModel.obtainEvent(it)
        }
    )
}

@Composable
private fun WeatherView(
    state: WeatherViewState,
    eventConsumer: (WeatherEvent) -> Unit,
) {
    Column {
        Text(state.title)
        Text(state.cityName)

        TextField(
            value = state.userName,
            onValueChange = {
                eventConsumer(WeatherEvent.OnNameChange(it))
            },
            isError = state.errorUserName != null,
        )

        FooterButton(
            text = state.userName,
            onClick = {
                eventConsumer(WeatherEvent.OnSearchClick)
            },
        )
    }
}

@Composable
private fun FooterButton(
    text: String,
    onClick: () -> Unit,
) {
    Button(onClick = onClick) {
        Text(text)
    }
}

@Preview
@Composable
private fun WeatherView_Preview() {
    WeatherView(
        WeatherViewState(
            userName = "asd"
        )
    ) {}
}

@Preview
@Composable
private fun WeatherView_Preview_Error() {
    WeatherView(
        WeatherViewState(
            userName = "asd",
            errorUserName = "Error",
        )
    ) {}
}

