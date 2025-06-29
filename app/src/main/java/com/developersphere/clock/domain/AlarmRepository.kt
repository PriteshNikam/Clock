package com.developersphere.clock.domain

import com.developersphere.clock.domain.model.AlarmDataEntity
import kotlinx.coroutines.flow.Flow

interface AlarmRepository {
    suspend fun getAllAlarm(): Flow<List<AlarmDataEntity>>

    suspend fun addAlarm(alarmEntity: AlarmDataEntity)

    suspend fun deleteAlarm(alarm: AlarmDataEntity)
}