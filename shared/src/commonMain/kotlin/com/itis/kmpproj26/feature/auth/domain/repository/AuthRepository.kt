package com.itis.kmpproj26.feature.auth.domain.repository

import com.itis.kmpproj26.core.util.result.Result
import com.itis.kmpproj26.feature.auth.domain.model.User

interface AuthRepository {

    suspend fun saveToken(token: String): Result<Boolean>
    suspend fun getToken(): Result<String?>
    suspend fun clearToken(): Result<Boolean>
    suspend fun getUserByToken(token: String): Result<User>
    suspend fun getUserByEmail(email: String): Result<User>
    suspend fun getTokenByEmailAndPassword(
        email: String,
        password: String,
    ): Result<String>

    suspend fun createUser(
        email: String,
        password: String,
        firstName: String,
        lastName: String,
    ): Result<String>

    suspend fun deleteUser(token: String): Result<Boolean>
}