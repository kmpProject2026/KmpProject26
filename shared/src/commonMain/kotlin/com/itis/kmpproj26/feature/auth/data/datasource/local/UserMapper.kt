package com.itis.kmpproj26.feature.auth.data.datasource.local

import com.itis.kmpproj26.Users
import com.itis.kmpproj26.core.util.date.DateHelper
import com.itis.kmpproj26.feature.auth.domain.model.User


fun Users.mapToDomain(): User {
    return User(
        email = email,
        firstName = first_name,
        lastName = last_name,
        createdAt = DateHelper.fromMillis(created_at),
    )
}
