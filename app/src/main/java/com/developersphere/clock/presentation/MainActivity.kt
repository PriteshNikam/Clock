package com.developersphere.clock.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.developersphere.clock.presentation.screens.HomeScreen
import com.developersphere.clock.ui.theme.ClockTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ClockTheme {
                HomeScreen()
            }
        }
    }
}
