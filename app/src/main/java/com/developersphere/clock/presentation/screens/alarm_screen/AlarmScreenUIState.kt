package com.developersphere.clock.presentation.screens.alarm_screen

import android.os.Build
import androidx.annotation.RequiresApi
import com.developersphere.clock.domain.enum.Day
import com.developersphere.clock.domain.enum.Period
import com.developersphere.clock.domain.model.AlarmDataEntity
import com.developersphere.clock.utils.TimeConversion

@RequiresApi(Build.VERSION_CODES.O)
data class AlarmScreenUiState(
    val currentTime: String = TimeConversion.getCurrentFormattedTime(),
    val currentAlarmId: Int? = null,
    val selectedHour: Int = 6,
    val selectedMinute: Int = 0,
    val selectedPeriod: Period = Period.AM,
    val alarms: List<AlarmDataEntity> = emptyList(),
    val alarmTitle: String = "",
    val alarmOnDays: Map<Day, Boolean> = emptyMap(),
    val isAlarmActive: Boolean = false,
)