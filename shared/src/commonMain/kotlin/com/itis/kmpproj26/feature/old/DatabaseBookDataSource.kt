package com.itis.kmpproj26.feature.old

import com.itis.kmpproj26.BookDB
import com.itis.kmpproj26.Database
import com.russhwolf.settings.Settings
import com.russhwolf.settings.nullableString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class DatabaseBookDataSource(
    private val database: Database,
    private val settings: Settings,
) {

    var appName: String? by settings.nullableString("app_name")

    suspend fun getAll() = withContext(Dispatchers.IO) {
        database.bookQueries.getAll().executeAsList()
    }

    suspend fun addBook() = withContext(Dispatchers.IO) {
        database.bookQueries.insert(
            bookDB = BookDB(
                id = 2504,
                name = "Shawn Lopez",
                number = 1548,
                test = 1991
            )
        )
    }
}