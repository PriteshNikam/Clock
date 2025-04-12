package com.developersphere.clock.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.developersphere.clock.R
import com.developersphere.clock.presentation.screens.ClockScreen
import com.developersphere.clock.presentation.screens.StopWatchScreen
import com.developersphere.clock.presentation.screens.TimerScreen
import com.developersphere.clock.presentation.screens.alarm_screen.AlarmScreen
import com.developersphere.clock.presentation.screens.create_alarm_screen.AddAlarmScreen
import kotlinx.serialization.Serializable

enum class ScreenEnum {
    Alarm,
    Clock,
    StopWatch,
    Timer,
    CreateAlarmScreen
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation(navigationController: NavHostController) {
    NavHost(navController = navigationController, startDestination = Screen.Alarm) {

        /**
        this is not type safe navigation
        this is string base route navigation
         ** when you use make sure while navigating you pass route not the class.
         */
//        composable(Screen.Alarm.route){
//            AlarmScreen {screen ->
//                navigationController.navigate(screen)
//            }
//        }

        /**
        this is type safe navigation her you pass the class for routing
         */
        composable<Screen.Alarm> {
            AlarmScreen { screen ->
                navigationController.navigate(screen)
            }
        }

        composable<Screen.Clock> { ClockScreen() }
        composable<Screen.Timer> { TimerScreen() }
        composable<Screen.StopWatch> { StopWatchScreen() }
        composable<Screen.AddAlarmScreen> { navBackStackEntry ->
            val screenData = navBackStackEntry.toRoute<Screen.AddAlarmScreen>()
            val id = screenData.alarmId
            val alarmTitle = screenData.alarmTitle
            AddAlarmScreen(alarmId = id, alarmTitle = alarmTitle)
        }
    }
}

@Serializable
sealed class Screen(val route: String, val iconId: Int? = null) {
    @Serializable
    data object Alarm : Screen(route = ScreenEnum.Alarm.name, iconId = R.drawable.alarm_clock)

    @Serializable
    data object Clock : Screen(route = ScreenEnum.Clock.name, iconId = R.drawable.clock)

    @Serializable
    data object StopWatch : Screen(route = ScreenEnum.StopWatch.name, iconId = R.drawable.timer)

    @Serializable
    data object Timer : Screen(route = ScreenEnum.Timer.name, iconId = R.drawable.stopwatch)

    @Serializable
    data class AddAlarmScreen(val alarmId: Int, val alarmTitle: String) :
        Screen(ScreenEnum.CreateAlarmScreen.name)
}
