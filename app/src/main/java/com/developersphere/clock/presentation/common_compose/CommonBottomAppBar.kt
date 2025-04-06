package com.developersphere.clock.presentation.common_compose

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.developersphere.clock.presentation.navigation.Screen


@Composable
fun CommonBottomAppBar(navController: NavController) {

    val bottomNavigationRoutes = listOf(
        Screen.Alarm,
        Screen.Clock,
        Screen.Timer,
        Screen.StopWatch,
        )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route

    NavigationBar(containerColor = Color(0xff363E46)
    ) {
        bottomNavigationRoutes.forEach { screen ->
            NavigationBarItem(
                selected = currentDestination == screen.route,
                onClick = {
                    if(currentDestination != screen.route) {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = screen.route,
                    )
                },
                alwaysShowLabel = true,
                label = { Text(text = screen.route) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Green,
                    unselectedIconColor = Color.White,
                    selectedTextColor = Color.Green,
                    indicatorColor = Color.Transparent,
                ),
            )
        }
    }
}