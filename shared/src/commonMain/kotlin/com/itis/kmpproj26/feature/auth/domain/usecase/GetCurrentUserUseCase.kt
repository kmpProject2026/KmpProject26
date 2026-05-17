package com.itis.kmpproj26.feature.auth.domain.usecase

import com.itis.kmpproj26.core.util.result.Result
import com.itis.kmpproj26.feature.auth.domain.model.User

interface GetCurrentUserUseCase {
    suspend operator fun invoke(): Result<User>
}
