package com.developersphere.clock.domain

import android.util.Log
import com.developersphere.clock.domain.model.AlarmDataEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AlarmUsecase @Inject constructor(private val alarmRepository: AlarmRepository) {

    suspend fun getAllAlarm(): Flow<List<AlarmDataEntity>> {
        return alarmRepository.getAllAlarm()
    }

    suspend fun addAlarm(alarmEntity: AlarmDataEntity) {
        return alarmRepository.addAlarm(alarmEntity)
    }

    suspend fun deleteAlarm(alarmEntity: AlarmDataEntity) {
        return alarmRepository.deleteAlarm(alarmEntity)
    }
}