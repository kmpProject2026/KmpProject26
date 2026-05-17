package com.itis.kmpproj26

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform