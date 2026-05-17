package com.itis.kmpproj26.feature.auth.data.repository

import com.itis.kmpproj26.core.util.result.FailureReason
import com.itis.kmpproj26.core.util.result.Result
import com.itis.kmpproj26.feature.auth.data.datasource.PersistentAuthDataSource
import com.itis.kmpproj26.feature.auth.data.datasource.local.mapToDomain
import com.itis.kmpproj26.feature.auth.domain.model.User
import com.itis.kmpproj26.feature.auth.domain.repository.AuthRepository
import korlibs.crypto.sha256
import kotlinx.uuid.UUID
import kotlinx.uuid.generateUUID

internal class AuthRepositoryImpl(
    private val persistentDataSource: PersistentAuthDataSource,
) : AuthRepository {

    override suspend fun saveToken(token: String): Result<Boolean> {
        return try {
            persistentDataSource.saveToken(token)
            Result.Success(true)
        } catch (_: Exception) {
            Result.Failure(FailureReason.Unknown())
        }
    }

    override suspend fun getToken(): Result<String?> {
        return try {
            val token = persistentDataSource.getToken()
            Result.Success(token)
        } catch (_: Exception) {
            Result.Failure(FailureReason.Unknown())
        }
    }

    override suspend fun clearToken(): Result<Boolean> {
        return try {
            persistentDataSource.clearToken()
            Result.Success(true)
        } catch (_: Exception) {
            Result.Failure(FailureReason.Unknown())
        }
    }

    override suspend fun getUserByToken(token: String): Result<User> {
        return try {
            val entity = persistentDataSource.getUserByToken(token)
                ?: return Result.Failure(FailureReason.BadRequest())
            Result.Success(entity.mapToDomain())
        } catch (_: Exception) {
            Result.Failure(FailureReason.Unknown())
        }
    }

    override suspend fun getUserByEmail(email: String): Result<User> {
        return try {
            val entity = persistentDataSource.getUserByEmail(email)
                ?: return Result.Failure(FailureReason.BadRequest())
            Result.Success(entity.mapToDomain())
        } catch (_: Exception) {
            Result.Failure(FailureReason.Unknown())
        }
    }

    override suspend fun getTokenByEmailAndPassword(
        email: String,
        password: String,
    ): Result<String> {
        return try {
            val hashedPassword = hashPassword(password)
            val entity = persistentDataSource.getUserByEmailAndPassword(
                email = email,
                password = hashedPassword
            ) ?: return Result.Failure(FailureReason.BadRequest())
            Result.Success(entity.token)
        } catch (_: Exception) {
            Result.Failure(FailureReason.Unknown())
        }
    }

    override suspend fun createUser(
        email: String,
        password: String,
        firstName: String,
        lastName: String,
    ): Result<String> {
        return try {
            val token = generateToken()
            val hashedPassword = hashPassword(password)

            persistentDataSource.createUser(
                token = token,
                email = email,
                password = hashedPassword,
                firstName = firstName,
                lastName = lastName,
            )

            Result.Success(token)
        } catch (e: Exception) {
            if (e.message?.contains("constraint") == true) {
                Result.Failure(FailureReason.Conflict())
            } else {
                Result.Failure(FailureReason.Unknown())
            }
        }
    }

    override suspend fun deleteUser(token: String): Result<Boolean> {
        return try {
            persistentDataSource.deleteUser(token)
            Result.Success(true)
        } catch (_: NoSuchElementException) {
            Result.Failure(FailureReason.BadRequest())
        } catch (_: Exception) {
            Result.Failure(FailureReason.Unknown())
        }
    }

    private fun generateToken(): String =
        UUID.generateUUID().toString()

    private fun hashPassword(password: String): String {
        return password.encodeToByteArray()
            .sha256()
            .hex
    }
}
