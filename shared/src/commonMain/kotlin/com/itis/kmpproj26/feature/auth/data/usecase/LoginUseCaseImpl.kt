package com.itis.kmpproj26.feature.auth.data.usecase

import com.itis.kmpproj26.core.util.result.Result
import com.itis.kmpproj26.feature.auth.domain.repository.AuthRepository
import com.itis.kmpproj26.feature.auth.domain.usecase.LoginUseCase

class LoginUseCaseImpl (
    private val authRepository: AuthRepository,
) : LoginUseCase {

    override suspend operator fun invoke(
        email: String,
        password: String,
    ): Result<Boolean> =

        when (val tokenResult = authRepository.getTokenByEmailAndPassword(email, password)) {
            is Result.Success -> {
                val token = tokenResult.data

                when (val saveResult = authRepository.saveToken(token)) {
                    is Result.Success -> Result.Success(true)
                    is Result.Failure -> Result.Failure(saveResult.reason)
                }
            }

            is Result.Failure -> {
                Result.Failure(tokenResult.reason)
            }
        }

}
