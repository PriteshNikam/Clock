package com.developersphere.clock.utils

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
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

    @RequiresApi(Build.VERSION_CODES.O)
    fun formatLocalTimeIntoHourMinute(localDateTime: String): String {
        try {
            val dateTime = LocalDateTime.parse(localDateTime)
            val formatter = DateTimeFormatter.ofPattern("hh:mm a")
            return dateTime.format(formatter)
        } catch (exception: Exception) {
            Log.d("Clock", "Time conversion exception :: $exception")
        }
        return ""
    }
}