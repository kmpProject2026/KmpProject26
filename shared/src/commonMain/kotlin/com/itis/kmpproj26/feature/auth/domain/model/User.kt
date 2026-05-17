package com.itis.kmpproj26.feature.auth.domain.model

import kotlinx.datetime.LocalDateTime

data class User(
    val email: String,
    val firstName: String,
    val lastName: String,
    val createdAt: LocalDateTime,
)
