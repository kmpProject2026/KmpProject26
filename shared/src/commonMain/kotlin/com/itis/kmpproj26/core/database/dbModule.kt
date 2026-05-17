package com.itis.kmpproj26.core.database

import app.cash.sqldelight.db.SqlDriver
import com.itis.kmpproj26.Database
import com.itis.kmpproj26.feature.old.DatabaseBookDataSource
import org.koin.dsl.module

val dbModule = module {

    single<Database> {
        Database(get<SqlDriver>())
    }

    factory {
        DatabaseBookDataSource(get(), get())
    }
}