package com.itis.kmpproj26.feature.weather.data.datasource

import com.itis.kmpproj26.Database
import com.itis.kmpproj26.WeatherDB
import com.itis.kmpproj26.core.settings.json
import com.russhwolf.settings.Settings
import com.russhwolf.settings.boolean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class TestPerson(
    @SerialName("age")
    val name: String,
)

internal class PersistentWeatherDataSource(
    private val database: Database,
    private val settings: Settings,
    private val json: Json,
) {

    var isFirstLaunch by settings.boolean("is_first_launch", false)

    var person by settings.json(
        key = "key",
        json = json,
        TestPerson.serializer()
    )

    suspend fun test() = withContext(Dispatchers.IO) {
        database.weatherQueries.insertWeatherDb(
            WeatherDB(id = 5431, name = "Eula Roberts", temp = 10.2)
        )
        database.weatherQueries.getAll().executeAsList()
    }
}