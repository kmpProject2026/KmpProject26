package com.itis.kmpproj26

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Kmpproj26native",
    ) {
        App()
    }
}