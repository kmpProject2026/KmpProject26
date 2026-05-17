package com.itis.kmpproj26.feature.auth.data.datasource

import com.itis.kmpproj26.Database
import com.itis.kmpproj26.Users
import com.russhwolf.settings.Settings
import com.russhwolf.settings.set
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlin.time.Clock

internal class PersistentAuthDataSource(
    private val database: Database,
    private val settings: Settings,
) {

    private companion object {
        const val TOKEN_KEY = "jwt"
    }

    // ---------- TOKEN ----------

    fun saveToken(token: String) {
        settings[TOKEN_KEY] = token
    }

    fun getToken(): String? {
        return settings.getStringOrNull(TOKEN_KEY)
    }

    fun clearToken() {
        settings.remove(TOKEN_KEY)
    }

    // ---------- USER ----------

    suspend fun getUserByToken(token: String): Users? {
        return withContext(Dispatchers.IO) {
            database.аuthQueries
                .getUserByToken(token)
                .executeAsOneOrNull()
        }
    }

    suspend fun getUserByEmail(email: String): Users? {
        return withContext(Dispatchers.IO) {
            database.аuthQueries
                .getUserByEmail(email)
                .executeAsOneOrNull()
        }
    }

    suspend fun getUserByEmailAndPassword(
        email: String,
        password: String,
    ): Users? {
        return withContext(Dispatchers.IO) {
            database.аuthQueries
                .getUserByEmailAndPassword(email, password)
                .executeAsOneOrNull()
        }
    }

    suspend fun createUser(
        token: String,
        email: String,
        password: String,
        firstName: String,
        lastName: String,
    ): String {
        return withContext(Dispatchers.IO) {

            val now = Clock.System.now().toEpochMilliseconds()

            database.аuthQueries.insertUser(
                email = email,
                password = password,
                token = token,
                first_name = firstName,
                last_name = lastName,
                created_at = now
            )

            token
        }
    }

    suspend fun deleteUser(token: String) {
        return withContext(Dispatchers.IO) {
            val rows = database.аuthQueries.deleteByToken(token)

            if (rows.value == 0L) throw NoSuchElementException()
        }
    }
}