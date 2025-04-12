package com.developersphere.clock.presentation.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.developersphere.clock.presentation.common_compose.CommonBottomAppBar
import com.developersphere.clock.presentation.navigation.AppNavigation

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            CommonBottomAppBar(navController)
        }
    ) {
        Box(modifier = Modifier.padding(it)){
            AppNavigation(navController)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}