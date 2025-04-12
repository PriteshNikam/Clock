package com.developersphere.clock.presentation.navigation.routes

import com.developersphere.clock.R
import kotlinx.serialization.Serializable


enum class BottomScreenEnum {
    Alarm,
    Clock,
    StopWatch,
    Timer,
}

@Serializable
sealed class BottomNavRoute(val route: String, val iconId: Int? = null) {
    @Serializable
    data object Alarm: BottomNavRoute(route = BottomScreenEnum.Alarm.name, iconId = R.drawable.alarm_clock)
    @Serializable
    data object Clock : BottomNavRoute(route = BottomScreenEnum.Clock.name, iconId = R.drawable.clock)

    @Serializable
    data object StopWatch : BottomNavRoute(route = BottomScreenEnum.StopWatch.name, iconId = R.drawable.timer)

    @Serializable
    data object Timer : BottomNavRoute(route = BottomScreenEnum.Timer.name, iconId = R.drawable.stopwatch)
}