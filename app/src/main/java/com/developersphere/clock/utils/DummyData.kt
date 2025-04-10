package com.developersphere.clock.utils

import com.developersphere.clock.domain.enum.Days
import com.developersphere.clock.domain.model.AlarmData

object DummyData {
    private val demoAlarm = AlarmData(
        title = "testing",
        "8:30 am",
        onDays = mutableMapOf(
            Days.Sunday to false,
            Days.Monday to true,
            Days.Tuesday to true,
            Days.Wednesday to true,
            Days.Thursday to true,
            Days.Friday to true,
            Days.Saturday to false,
        ),
        isActive = false
    )
    val alarmScreenData = mutableListOf(
        demoAlarm,
        demoAlarm.copy(isActive = true),
        demoAlarm,
        demoAlarm.copy(isActive = true),
        demoAlarm,
        demoAlarm,
        demoAlarm.copy(isActive = true),
        demoAlarm,
        demoAlarm.copy(isActive = true),
        demoAlarm
    )
}