package com.developersphere.clock.utils

import java.util.Locale

object StringFormatter {
    fun formatTimeInMinSecMilliSec(timeInMillSecond: Long): String {
        val minute = (timeInMillSecond / 1000) / 60 // gives minutes
        val seconds = (timeInMillSecond / 1000) % 60 // gives seconds
        val millis = (timeInMillSecond % 1000) / 10 // give milli seconds.

        return String.format(Locale.US, "%02d : %02d : %02d", minute, seconds, millis)
    }

    fun formatInHourMinuteSeconds(timeInMilliSeconds: Int): String {
        val hour = (timeInMilliSeconds / 3600).toString().padStart(2, '0')
        val minute = ((timeInMilliSeconds % 3600) / 60).toString().padStart(2, '0')
        val seconds = (timeInMilliSeconds % 60).toString().padStart(2, '0')
        return "${hour}:${minute}:${seconds}"
    }
}