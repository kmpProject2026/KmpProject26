package com.itis.kmpproj26.core.settings

import com.russhwolf.settings.Settings
import com.russhwolf.settings.set
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

internal fun <T> Settings.json(
    key: String? = null,
    json: Json,
    serializer: KSerializer<T>,
): ReadWriteProperty<Any?, T?> = SettingsObjectDelegate(
    settings = this,
    json = json,
    serializer = serializer,
    key = key,
)

private class SettingsObjectDelegate<T>(
    private val settings: Settings,
    private val json: Json,
    private val serializer: KSerializer<T>,
    private val key: String?,
) : ReadWriteProperty<Any?, T?> {

    override fun getValue(thisRef: Any?, property: KProperty<*>): T? =
        settings.getStringOrNull(key ?: property.name)?.let { jsonString ->
            json.decodeFromString(serializer, jsonString)
        }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) =
        when (value) {
            null -> settings.remove(key ?: property.name)
            else -> settings[key ?: property.name] = json.encodeToString(serializer, value)
        }
}
