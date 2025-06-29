package com.developersphere.clock.data.repository

import com.developersphere.clock.data.local.AlarmDao
import com.developersphere.clock.data.local.toAlarmData
import com.developersphere.clock.data.local.toAlarmDataEntity
import com.developersphere.clock.domain.AlarmRepository
import com.developersphere.clock.domain.model.AlarmDataEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AlarmRepositoryImp @Inject constructor(private val alarmDao: AlarmDao) : AlarmRepository {
    override suspend fun getAllAlarm(): Flow<List<AlarmDataEntity>> {
        return alarmDao.getAllAlarm().map { alarmEntityList ->
            alarmEntityList?.map { it.toAlarmDataEntity() } ?: emptyList()
        }
    }

    override suspend fun addAlarm(alarmEntity: AlarmDataEntity) {
        return alarmDao.insertAlarm(alarmEntity.toAlarmData())
    }

    override suspend fun deleteAlarm(alarm: AlarmDataEntity) {
        return alarmDao.deleteAlarm(alarm.toAlarmData())
    }
}