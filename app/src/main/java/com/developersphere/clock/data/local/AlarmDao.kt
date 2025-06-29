package com.developersphere.clock.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface  AlarmDao{
    @Query("Select * from alarm_table")
    fun getAllAlarm(): Flow<List<AlarmEntity>?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlarm(alarm:AlarmEntity)

    @Delete
    suspend fun deleteAlarm(alarm: AlarmEntity)
}