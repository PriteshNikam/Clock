package com.developersphere.clock.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.developersphere.clock.R
import com.developersphere.clock.presentation.screens.alarm_screen.AlarmScreen
import com.developersphere.clock.presentation.screens.ClockScreen
import com.developersphere.clock.presentation.screens.StopWatchScreen
import com.developersphere.clock.presentation.screens.TimerScreen
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
sealed class Screen(val route: String, val iconId: Int) {
    @Serializable
    data object Alarm : Screen(route = "Alarm", iconId = R.drawable.alarm_clock)

    @Serializable
    data object Clock : Screen(route = "Clock", iconId = R.drawable.clock)

    @Serializable
    data object StopWatch : Screen(route = "Stop Watch", iconId = R.drawable.timer)

    @Serializable
    data object Timer : Screen(route = "Timer", iconId = R.drawable.stopwatch)
}
