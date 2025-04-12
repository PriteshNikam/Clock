package com.developersphere.clock.presentation.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.developersphere.clock.presentation.navigation.routes.AlarmRoute
import com.developersphere.clock.presentation.navigation.routes.BottomNavRoute
import com.developersphere.clock.presentation.screens.DummyScreen
import com.developersphere.clock.presentation.screens.add_alarm_screen.AddAlarmScreen
import com.developersphere.clock.presentation.screens.alarm_screen.AlarmScreen

fun NavGraphBuilder.alarmScreenGraph(navigationController: NavController) {

    navigation<BottomNavRoute.Alarm>(
        startDestination = AlarmRoute.AlarmScreen,
    ) {
        composable<AlarmRoute.AlarmScreen> {
            RegisterAlarmScreen(navigationController)
        }

        composable<AlarmRoute.AddAlarmScreen> { navBackStackEntry ->
            RegisterAddAlarmScreen(navigationController, navBackStackEntry)
        }

        composable<AlarmRoute.DummyScreen> {
            RegisterDummyScreen(navigationController)
        }
    }
}

@Composable
fun RegisterAlarmScreen(navigationController: NavController) {
    AlarmScreen { screen ->
        navigationController.navigate(screen) {
            popUpTo<AlarmRoute.AlarmScreen> {
                saveState = true
            }
            launchSingleTop = true
        }

    }
}

@Composable
fun RegisterAddAlarmScreen(
    navigationController: NavController,
    navBackStackEntry: NavBackStackEntry,
) {
    val screenData = navBackStackEntry.toRoute<AlarmRoute.AddAlarmScreen>()
    val id = screenData.alarmId
    val alarmTitle = screenData.alarmTitle
    AddAlarmScreen(
        alarmId = id,
        alarmTitle = alarmTitle,
        navigation = { destination ->
            if (destination != null) {
                navigationController.navigate(destination)
            } else {
                navigationController.popBackStack()
            }
        }
    )
}

@Composable
fun RegisterDummyScreen(navigationController: NavController) {
    DummyScreen(
        navigation = { destination ->
            if (destination != null) {
                navigationController.navigate(destination) {
                    popUpTo<AlarmRoute.AlarmScreen> {
                        inclusive = true
                        saveState = true
                    }
                }
            } else {
                navigationController.popBackStack()
            }
        })
}


