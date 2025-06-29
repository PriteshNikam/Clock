package com.developersphere.clock.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [AlarmEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class AlarmDataBase: RoomDatabase(){
    abstract fun AlarmDao() : AlarmDao
}