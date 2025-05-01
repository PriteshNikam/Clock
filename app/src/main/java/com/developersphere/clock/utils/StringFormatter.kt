package com.developersphere.clock.utils

import java.util.Locale

object StringFormatter {
    fun formatTimer(timeInMillSecond: Long): String {
        val minute = (timeInMillSecond / 1000) / 60 // gives minutes
        val seconds = (timeInMillSecond / 1000) % 60 // gives seconds
        val millis = (timeInMillSecond % 1000) / 10 // give milli seconds.

        return String.format(Locale.US, "%02d : %02d : %02d", minute, seconds, millis)

    }
}