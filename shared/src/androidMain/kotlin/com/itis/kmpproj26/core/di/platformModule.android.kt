package com.itis.kmpproj26.core.di

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.itis.kmpproj26.Database
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

actual val platformModule: Module = module {
    single<SqlDriver> {
        AndroidSqliteDriver(
            schema = Database.Schema,
            context = get<Context>(),
            name = get(named<QualifierDBName>()),
        )
    }

    single<Settings> {
        SharedPreferencesSettings(
            delegate = get<Context>()
                .getSharedPreferences(
                    get(named<QualifierSettingName>()),
                    Context.MODE_PRIVATE
                )
        )
    }
}

// datastore + Room