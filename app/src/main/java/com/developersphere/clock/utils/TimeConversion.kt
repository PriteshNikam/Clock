package com.developersphere.clock.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

object TimeConversion {
    @RequiresApi(Build.VERSION_CODES.O)
    fun convertIntoLocalDateTime(hour:Int, minute:Int): LocalDateTime {
        return LocalDateTime.of(
            LocalDate.now(),
            LocalTime.of(hour, minute),
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getCurrentFormattedTime(): String {
        val formatter = DateTimeFormatter.ofPattern("hh:mm:ss a")
        return LocalTime.now().format(formatter)
    }
}