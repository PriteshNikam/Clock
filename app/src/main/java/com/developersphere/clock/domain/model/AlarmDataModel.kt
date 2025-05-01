package com.developersphere.clock.domain.model

import com.developersphere.clock.domain.enum.Day

data class AlarmData(
    var title: String? = null,
    var time: String? = null,
    var onDay: MutableMap<Day, Boolean>? = null,
    var isActive: Boolean? = null,
)