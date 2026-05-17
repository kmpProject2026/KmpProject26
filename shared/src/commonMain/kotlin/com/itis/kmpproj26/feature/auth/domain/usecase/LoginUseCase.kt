package com.itis.kmpproj26.feature.auth.domain.usecase

import com.itis.kmpproj26.core.util.result.Result

interface LoginUseCase {
    suspend operator fun invoke(email: String, password: String): Result<Boolean>
}
