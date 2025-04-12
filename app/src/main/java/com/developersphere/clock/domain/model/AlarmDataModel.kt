package com.developersphere.clock.domain.model

import com.developersphere.clock.domain.enum.Days

data class AlarmData(
    var title: String? = null,
    var time: String? = null,
    var onDays: MutableMap<Days, Boolean>? = null,
    var isActive: Boolean? = null,
)