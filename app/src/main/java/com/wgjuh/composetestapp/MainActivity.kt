package com.wgjuh.composetestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.wgjuh.composetestapp.meet.*
import com.wgjuh.composetestapp.ui.theme.ComposeTestAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTestAppTheme {
                Snapshot { items, heatriangleColorer->
                    Ver5(
                        items = items,
                        triangleColor = heatriangleColorer,
                    )
                }
            }
        }
    }
}
