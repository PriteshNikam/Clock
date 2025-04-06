package com.developersphere.clock.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.developersphere.clock.presentation.screens.AlarmScreen
import com.developersphere.clock.presentation.screens.ClockScreen
import com.developersphere.clock.presentation.screens.StopWatchScreen
import com.developersphere.clock.presentation.screens.TimerScreen
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Composable
fun AppNavigation(navigationController: NavHostController) {
    NavHost(navController = navigationController, startDestination = Screen.Alarm.route) {
        composable(Screen.Alarm.route) { AlarmScreen() }
        composable(Screen.Clock.route) { ClockScreen() }
        composable(Screen.Timer.route) { TimerScreen() }
        composable(Screen.StopWatch.route) { StopWatchScreen() }
    }
}

@Serializable
sealed class Screen(val route: String, val icon:@Contextual ImageVector) {
    @Serializable
    data object Alarm : Screen(route = "Alarm",icon  = Icons.Default.Home)

    @Serializable
    data object Clock : Screen(route = "Clock",icon  = Icons.Default.Home)

    @Serializable
    data object StopWatch : Screen(route = "Stop Watch",icon  = Icons.Default.Home)

    @Serializable
    data object Timer : Screen(route = "Timer",icon  = Icons.Default.Home)
}
