package com.developersphere.clock.presentation.navigation.routes

import com.developersphere.clock.R
import com.developersphere.clock.domain.enum.BottomScreen
import kotlinx.serialization.Serializable

@Serializable
sealed class BottomNavRoute(val route: String, val iconId: Int? = null) {
    @Serializable
    data object Alarm: BottomNavRoute(route = BottomScreen.Alarm.name, iconId = R.drawable.alarm_clock)
    @Serializable
    data object Clock : BottomNavRoute(route = BottomScreen.Clock.name, iconId = R.drawable.clock)

    @Serializable
    data object StopWatch : BottomNavRoute(route = BottomScreen.StopWatch.name, iconId = R.drawable.timer)

    @Serializable
    data object Timer : BottomNavRoute(route = BottomScreen.Timer.name, iconId = R.drawable.stopwatch)
}