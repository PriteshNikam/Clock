package com.developersphere.clock.utils

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import com.developersphere.clock.domain.model.AlarmDataEntity
import com.developersphere.clock.utils.AppConstants.ALARM_MESSAGE
import com.developersphere.clock.utils.AppConstants.START_ALARM
import java.time.ZoneId

interface AlarmScheduler {
    fun schedule(alarmItem: AlarmDataEntity)
    fun cancel(alarmItem: AlarmDataEntity)
}

@RequiresApi(Build.VERSION_CODES.O)
class AlarmSchedulerImpl(private val context: Context) : AlarmScheduler {

    private val alarmManager = context.getSystemService(AlarmManager::class.java)

    override fun schedule(alarmItem: AlarmDataEntity) {
        val startAlarmIntent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra(ALARM_MESSAGE, alarmItem.title)
            action = START_ALARM
        }
        val alarmTime = alarmItem.alarmTime
            ?.atZone(ZoneId.systemDefault())
            ?.toInstant()
            ?.toEpochMilli()

        // intent to start alarm
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            alarmItem.hashCode(),
            startAlarmIntent,
            PendingIntent.FLAG_CANCEL_CURRENT or PendingIntent.FLAG_MUTABLE, // Mutable for Android 12+
        )

        // set alarm
        alarmManager.setAlarmClock(
            AlarmManager.AlarmClockInfo(alarmTime ?: 0, pendingIntent),
            pendingIntent
        )
    }

    override fun cancel(alarmItem: AlarmDataEntity) {
        alarmManager.cancel(
            PendingIntent.getBroadcast(
                context,
                alarmItem.hashCode(),
                Intent(context, AlarmReceiver::class.java),
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
            )
        )
    }
}