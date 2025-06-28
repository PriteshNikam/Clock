package com.developersphere.clock.utils

import android.os.Build
import androidx.annotation.RequiresApi
import com.developersphere.clock.domain.enum.Day
import com.developersphere.clock.domain.model.AlarmData
import java.time.LocalDateTime

object DummyData {
    @RequiresApi(Build.VERSION_CODES.O)
    private val demoAlarm = AlarmData(
        title = "testing",
        alarmTime = LocalDateTime.now(),
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
    @RequiresApi(Build.VERSION_CODES.O)
    val alarmScreenData = mutableListOf(
        demoAlarm.copy(alarmId = 1),
        demoAlarm.copy(alarmId = 2,isActive = true),
        demoAlarm.copy(alarmId = 3),
        demoAlarm.copy(alarmId = 4,isActive = true),
        demoAlarm.copy(alarmId = 5),
        demoAlarm.copy(alarmId = 6),
        demoAlarm.copy(alarmId = 7,isActive = true),
        demoAlarm.copy(alarmId = 8),
        demoAlarm.copy(alarmId =9,isActive = true),
        demoAlarm.copy(alarmId = 10),
    )
}