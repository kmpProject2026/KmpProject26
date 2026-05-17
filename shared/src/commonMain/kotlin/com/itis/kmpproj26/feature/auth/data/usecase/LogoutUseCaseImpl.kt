package com.itis.kmpproj26.feature.auth.data.usecase

import com.itis.kmpproj26.core.util.result.Result
import com.itis.kmpproj26.feature.auth.domain.repository.AuthRepository
import com.itis.kmpproj26.feature.auth.domain.usecase.LogoutUseCase

class LogoutUseCaseImpl (
    private val authRepository: AuthRepository,
) : LogoutUseCase {

    override suspend operator fun invoke(): Result<Boolean> = authRepository.clearToken()
}
