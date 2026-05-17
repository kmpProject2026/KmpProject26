package com.itis.kmpproj26.feature.auth.domain.usecase

import com.itis.kmpproj26.core.util.result.Result

interface RegisterUseCase {
    suspend operator fun invoke(
        email: String,
        password: String,
        firstName: String,
        lastName: String,
    ): Result<Boolean>
}
