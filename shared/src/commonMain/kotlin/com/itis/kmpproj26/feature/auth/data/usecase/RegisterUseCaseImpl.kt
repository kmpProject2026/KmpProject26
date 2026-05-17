package com.itis.kmpproj26.feature.auth.data.usecase

import com.itis.kmpproj26.core.util.result.Result
import com.itis.kmpproj26.feature.auth.domain.repository.AuthRepository
import com.itis.kmpproj26.feature.auth.domain.usecase.RegisterUseCase

class RegisterUseCaseImpl (
    private val authRepository: AuthRepository,
) : RegisterUseCase {

    override suspend operator fun invoke(
        email: String,
        password: String,
        firstName: String,
        lastName: String,
    ): Result<Boolean> =

        when (val createResult = authRepository.createUser(
            email = email,
            password = password,
            firstName = firstName,
            lastName = lastName
        )) {
            is Result.Success -> {
                val token = createResult.data

                when (val saveResult = authRepository.saveToken(token)) {
                    is Result.Success -> Result.Success(true)
                    is Result.Failure -> Result.Failure(saveResult.reason)
                }
            }

            is Result.Failure -> {
                Result.Failure(createResult.reason)
            }
        }
}
