package com.itis.kmpproj26.core.database

import app.cash.sqldelight.db.SqlDriver
import com.itis.kmpproj26.Database
import org.koin.dsl.module

val dbModule = module {

    single<Database> {
        Database(get<SqlDriver>())
    }
}
