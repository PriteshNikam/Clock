package com.developersphere.clock.presentation.screens

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.developersphere.clock.presentation.common_compose.CommonBottomAppBar
import com.developersphere.clock.presentation.navigation.AppNavigation

@Composable
fun HomeScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            CommonBottomAppBar(navController)
        }
    ) {
        AppNavigation(navController)
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}