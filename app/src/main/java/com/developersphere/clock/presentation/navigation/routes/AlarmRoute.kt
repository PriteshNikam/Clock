package com.developersphere.clock.presentation.navigation.routes

import com.developersphere.clock.domain.enum.AlarmScreenEnum
import kotlinx.serialization.Serializable

@Serializable
sealed class AlarmRoute(val route: String) {

    @Serializable
    data object AlarmScreen : AlarmRoute(route = AlarmScreenEnum.Alarm.name)

    @Serializable
    data class AddAlarmScreen(val alarmId: Int?) :
        AlarmRoute(route = AlarmScreenEnum.AddAlarmScreen.name)

    @Serializable
    data object DummyScreen : AlarmRoute(route = AlarmScreenEnum.DummyScreen.name)
}