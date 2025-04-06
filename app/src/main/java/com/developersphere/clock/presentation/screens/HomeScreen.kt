package com.developersphere.clock.presentation.screens

import androidx.compose.foundation.layout.fillMaxSize
import com.developersphere.clock.presentation.common_compose.CommonBottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.developersphere.clock.presentation.navigation.AppNavigation
import java.lang.reflect.Modifier

@Composable
fun HomeScreen(){
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { CommonBottomAppBar(navController) }
    ){
        AppNavigation(navController)
    }
}