package com.developersphere.clock.utils

import com.developersphere.clock.domain.enum.Day
import com.developersphere.clock.domain.model.AlarmData

object DummyData {
    private val demoAlarm = AlarmData(
        title = "testing",
        "8:30 am",
        onDay = mutableMapOf(
            Day.Sunday to false,
            Day.Monday to true,
            Day.Tuesday to true,
            Day.Wednesday to true,
            Day.Thursday to true,
            Day.Friday to true,
            Day.Saturday to false,
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