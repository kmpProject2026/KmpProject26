package com.itis.kmpproj26.feature.auth.domain.usecase

import com.itis.kmpproj26.core.util.result.Result

interface IsUserLoggedInUseCase {
    suspend operator fun invoke(): Result<Boolean>
}
