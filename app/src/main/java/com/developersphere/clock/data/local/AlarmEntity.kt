package com.developersphere.clock.data.local

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.developersphere.clock.domain.enum.Day
import com.developersphere.clock.domain.model.AlarmDataEntity
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

@Entity("alarm_table")
class AlarmEntity(
    @PrimaryKey(autoGenerate = true)
    val alarmId:Int,
    val title: String? = null,
    val alarmTime: LocalDateTime? = null,
    val onDay: Map<Day, Boolean>? = null,
    val isActive: Boolean? = false,
)

fun AlarmDataEntity.toAlarmData(): AlarmEntity {
    return AlarmEntity(
        alarmId = this.alarmId ?: 0,
        title = this.title,
        alarmTime = this.alarmTime,
        onDay = this.onDay,
        isActive = this.isActive
    )
}

fun List<AlarmDataEntity>.toAlarmData(): List<AlarmEntity> {
    return this.map { it.toAlarmData() }
}

fun AlarmEntity.toAlarmDataEntity(): AlarmDataEntity {
    return AlarmDataEntity(
        alarmId = this.alarmId,
        title = this.title,
        alarmTime = this.alarmTime,
        onDay = this.onDay,
        isActive = this.isActive
    )
}




class Converters {
    @TypeConverter
    fun fromDayBooleanMap(map: Map<Day, Boolean>?): String? {
        return map?.entries?.joinToString(separator = ";") { "${it.key.name}:${it.value}" }
    }

    @TypeConverter
    fun toDayBooleanMap(data: String?): MutableMap<Day, Boolean>? {
        if (data.isNullOrEmpty()) return null
        val map = mutableMapOf<Day, Boolean>()
        data.split(";").forEach {
            val (dayStr, boolStr) = it.split(":")
            val day = Day.valueOf(dayStr)
            val bool = boolStr.toBoolean()
            map[day] = bool
        }
        return map
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun fromLocalDateTime(dateTime: LocalDateTime?): Long? {
        return dateTime?.atZone(ZoneId.systemDefault())?.toInstant()?.toEpochMilli()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun toLocalDateTime(epochMillis: Long?): LocalDateTime? {
        return epochMillis?.let {
            Instant.ofEpochMilli(it).atZone(ZoneId.systemDefault()).toLocalDateTime()
        }
    }
}