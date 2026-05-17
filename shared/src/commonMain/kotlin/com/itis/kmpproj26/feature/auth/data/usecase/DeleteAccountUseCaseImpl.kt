package com.itis.kmpproj26.feature.auth.data.usecase

import com.itis.kmpproj26.core.util.result.FailureReason
import com.itis.kmpproj26.core.util.result.Result
import com.itis.kmpproj26.feature.auth.domain.repository.AuthRepository
import com.itis.kmpproj26.feature.auth.domain.usecase.DeleteAccountUseCase

class DeleteAccountUseCaseImpl(
    private val authRepository: AuthRepository,
) : DeleteAccountUseCase {

    override suspend operator fun invoke(): Result<Boolean> =

         when (val tokenResult = authRepository.getToken()) {
            is Result.Success -> {
                val token = tokenResult.data ?: return Result.Failure(FailureReason.BadRequest())

                when (val deleteResult = authRepository.deleteUser(token)) {
                    is Result.Success -> {
                        when (val clearResult = authRepository.clearToken()) {
                            is Result.Success -> Result.Success(true)
                            is Result.Failure -> Result.Failure(clearResult.reason)
                        }
                    }

                    is Result.Failure -> {
                        if (deleteResult.reason is FailureReason.BadRequest) {
                            authRepository.clearToken()
                        }
                        Result.Failure(deleteResult.reason)
                    }
                }
            }

            is Result.Failure -> {
                Result.Failure(tokenResult.reason)
            }
        }
}
