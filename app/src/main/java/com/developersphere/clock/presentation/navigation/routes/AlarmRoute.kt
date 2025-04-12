package com.developersphere.clock.presentation.navigation.routes

import kotlinx.serialization.Serializable

enum class AlarmScreenEnum {
    Alarm,
    AddAlarmScreen,
    DummyScreen
}

@Serializable
sealed class AlarmRoute(val route: String) {

    @Serializable
    data object AlarmScreen : AlarmRoute(route = AlarmScreenEnum.Alarm.name)

    @Serializable
    data class AddAlarmScreen(val alarmId: Int, val alarmTitle: String) :
        AlarmRoute(route = AlarmScreenEnum.AddAlarmScreen.name)

    @Serializable
    data object DummyScreen : AlarmRoute(route = AlarmScreenEnum.DummyScreen.name)
}