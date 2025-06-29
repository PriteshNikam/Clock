package com.developersphere.clock.hilt

import android.content.Context
import androidx.room.Room
import com.developersphere.clock.data.local.AlarmDao
import com.developersphere.clock.data.local.AlarmDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AlarmDataBase {
        return Room.databaseBuilder(
            context = context,
            AlarmDataBase::class.java,
            "alarm-database"
        ).build()
    }

    @Provides
    fun provideAlarmDAO(database: AlarmDataBase): AlarmDao {
        return database.AlarmDao()
    }
}