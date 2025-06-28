package com.developersphere.clock.hilt

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.developersphere.clock.utils.AlarmScheduler
import com.developersphere.clock.utils.AlarmSchedulerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
@RequiresApi(Build.VERSION_CODES.O)
class AlarmModule {

    @Provides
    fun alarmScheduler(@ApplicationContext context: Context): AlarmScheduler {
        return AlarmSchedulerImpl(context)
    }
}