package com.developersphere.clock.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.developersphere.clock.presentation.navigation.graph.alarmScreenGraph
import com.developersphere.clock.presentation.navigation.routes.BottomNavRoute
import com.developersphere.clock.presentation.screens.clock_screen.ClockScreen
import com.developersphere.clock.presentation.screens.stop_watch_screen.StopWatchScreen
import com.developersphere.clock.presentation.screens.timer_screen.TimerScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavHost(navigationController: NavHostController) {
    NavHost(navController = navigationController, startDestination = BottomNavRoute.Alarm) {

        // nested graph
        alarmScreenGraph(navigationController)

        composable<BottomNavRoute.Clock> { ClockScreen() }
        composable<BottomNavRoute.Timer> { TimerScreen() }
        composable<BottomNavRoute.StopWatch> { StopWatchScreen() }
    }
}

