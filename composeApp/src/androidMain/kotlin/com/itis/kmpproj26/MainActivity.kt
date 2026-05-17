package com.itis.kmpproj26

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.itis.kmpproj26.feature.old.DatabaseBookDataSource
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val dbSource: DatabaseBookDataSource by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

//        lifecycleScope.launch {
//            dbSource.addBook()
//            val list = dbSource.getAll()
//            print(list.getOrNull(0))
//        }


        setContent {
            App()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}