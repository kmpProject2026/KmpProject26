package com.itis.kmpproj26

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.itis.kmpproj26.common.navigation.NavEntryBuilder
import com.itis.kmpproj26.common.navigation.NavHost
import com.itis.kmpproj26.common.ui.theme.DTheme
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val entryBuilders: Set<NavEntryBuilder> by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DTheme(darkTheme = isSystemInDarkTheme()) {
                NavHost(
                    entryBuilders = entryBuilders,
                )
            }
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}