package com.itis.kmpproj26.feature.auth.data.usecase

import com.itis.kmpproj26.core.util.result.FailureReason
import com.itis.kmpproj26.core.util.result.Result
import com.itis.kmpproj26.feature.auth.domain.model.User
import com.itis.kmpproj26.feature.auth.domain.repository.AuthRepository
import com.itis.kmpproj26.feature.auth.domain.usecase.GetCurrentUserUseCase

class GetCurrentUserUseCaseImpl (
    private val authRepository: AuthRepository,
) : GetCurrentUserUseCase {

    override suspend operator fun invoke(): Result<User> =

        when (val tokenResult = authRepository.getToken()) {
            is Result.Success -> {
                val token = tokenResult.data ?: return Result.Failure(FailureReason.BadRequest())

                when (val userResult = authRepository.getUserByToken(token)) {
                    is Result.Success -> userResult

                    is Result.Failure -> {
                        if (userResult.reason is FailureReason.BadRequest) {
                            authRepository.clearToken()
                        }
                        Result.Failure(userResult.reason)
                    }
                }
            }

            is Result.Failure -> {
                Result.Failure(tokenResult.reason)
            }
        }
}
