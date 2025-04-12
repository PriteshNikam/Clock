package com.developersphere.clock.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.developersphere.clock.R
import com.developersphere.clock.presentation.screens.ClockScreen
import com.developersphere.clock.presentation.screens.StopWatchScreen
import com.developersphere.clock.presentation.screens.TimerScreen
import kotlinx.serialization.Serializable

enum class ScreenEnum {
    Alarm,
    Clock,
    StopWatch,
    Timer,
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation(navigationController: NavHostController) {
    NavHost(navController = navigationController, startDestination = Screen.Alarm) {

        // nested graph
        alarmScreenGraph(navigationController)

        composable<Screen.Clock> { ClockScreen() }
        composable<Screen.Timer> { TimerScreen() }
        composable<Screen.StopWatch> { StopWatchScreen() }
    }
}

@Serializable
sealed class Screen(val route: String, val iconId: Int? = null) {
    @Serializable
    data object Alarm: Screen(route = ScreenEnum.Alarm.name, iconId = R.drawable.alarm_clock)
    @Serializable
    data object Clock : Screen(route = ScreenEnum.Clock.name, iconId = R.drawable.clock)

    @Serializable
    data object StopWatch : Screen(route = ScreenEnum.StopWatch.name, iconId = R.drawable.timer)

    @Serializable
    data object Timer : Screen(route = ScreenEnum.Timer.name, iconId = R.drawable.stopwatch)
}
