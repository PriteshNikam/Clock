package com.developersphere.clock.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.Ringtone
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import com.developersphere.clock.R
import com.developersphere.clock.utils.AppConstants.ALARM_MESSAGE
import com.developersphere.clock.utils.AppConstants.STOP_ALARM

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        // if action is STOP_ALARM, stop the ringtone and cancel the notification
        if (intent?.action == STOP_ALARM) {
            stopAlarmSound()
            val notificationManager =
                context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.cancel(1)
            return
        }

        val message = intent?.getStringExtra(ALARM_MESSAGE) ?: "Alarm!"
        val channelId = CHANNEL_ID

        context?.let { ctx ->
            // if android version is Oreo or higher, need to create a notification channel
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                    channelId,
                    "Alarm Notifications",
                    NotificationManager.IMPORTANCE_HIGH
                ).apply {
                    description = "Channel for alarm notifications"
                    enableLights(true)
                    lockscreenVisibility
                    enableVibration(true)
                    setSound(null, null)
                }
                val manager = ctx.getSystemService(NotificationManager::class.java)
                manager.createNotificationChannel(channel)
            }

            // Play the alarm ringtone
            playAlarmSound(ctx)

            // stop alarm intent.
            val stopAlarmIntent = Intent(ctx, AlarmReceiver::class.java).apply {
                action = STOP_ALARM
            }
            val stopPendingIntent = PendingIntent.getBroadcast(
                ctx,
                101,
                stopAlarmIntent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

            val builder = NotificationCompat.Builder(ctx, channelId)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Clock")
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .addAction(R.drawable.ic_launcher_foreground, "Stop Alarm", stopPendingIntent)
                .setAutoCancel(true)
                .setOngoing(true) // Until stopped manually

            val notificationManager =
                ctx.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(1, builder.build())
        }
    }

    companion object {
        const val CHANNEL_ID = "alarm_id"

        private var ringtone: Ringtone? = null

        fun playAlarmSound(context: Context) {
            val alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
            ringtone = RingtoneManager.getRingtone(context, alarmUri)
            ringtone?.play()
        }

        fun stopAlarmSound() {
            ringtone?.stop()
        }
    }
}
