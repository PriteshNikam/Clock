package com.developersphere.clock.domain.model

import com.developersphere.clock.domain.enum.Day
import java.time.LocalDateTime

data class AlarmData(
    val alarmId: Int? = null,
    var title: String? = null,
    var alarmTime: LocalDateTime? = null,
    var onDay: MutableMap<Day, Boolean>? = null,
    var isActive: Boolean? = null,
)