package com.developersphere.clock.presentation.navigation

import android.os.Build
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.developersphere.clock.presentation.screens.alarm_screen.AlarmScreen
import com.developersphere.clock.presentation.screens.create_alarm_screen.AddAlarmScreen
import kotlinx.serialization.Serializable

fun NavGraphBuilder.alarmScreenGraph(navigationController: NavController) {

    navigation<Screen.Alarm>(
        startDestination = AlarmScreenNav.AlarmScreen,
    ) {
        composable<AlarmScreenNav.AlarmScreen> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                AlarmScreen { screen ->
                    navigationController.navigate(screen){
                        popUpTo<AlarmScreenNav.AlarmScreen>{
                            saveState = true
                        }
                        launchSingleTop = true
                    }
                }
            }
        }

        composable<AlarmScreenNav.AddAlarmScreen> { navBackStackEntry ->
            val screenData = navBackStackEntry.toRoute<AlarmScreenNav.AddAlarmScreen>()
            val id = screenData.alarmId
            val alarmTitle = screenData.alarmTitle
            AddAlarmScreen(
                alarmId = id,
                alarmTitle = alarmTitle,
                navigation = {
                    navigationController.popBackStack()
                }
            )

        }

        composable<AlarmScreenNav.DummyScreen> { }
    }
}


enum class AlarmScreenEnum {
    Alarm,
    AddAlarmScreen,
    DummyScreen
}

@Serializable
sealed class AlarmScreenNav(val route: String) {

    @Serializable
    data object AlarmScreen : AlarmScreenNav(route = AlarmScreenEnum.Alarm.name)

    @Serializable
    data class AddAlarmScreen(val alarmId: Int, val alarmTitle: String) :
        AlarmScreenNav(route = AlarmScreenEnum.AddAlarmScreen.name)

    @Serializable
    data object DummyScreen : Screen(route = AlarmScreenEnum.DummyScreen.name)
}