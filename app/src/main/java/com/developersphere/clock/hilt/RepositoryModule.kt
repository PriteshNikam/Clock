package com.developersphere.clock.hilt

import com.developersphere.clock.data.repository.AlarmRepositoryImp
import com.developersphere.clock.domain.AlarmRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // Or ViewModelComponent::class, etc.
abstract class RepositoryModule {

    @Binds
    @Singleton // Optional: if you want a single instance throughout the app
    abstract fun bindAlarmRepository(
        alarmRepositoryImpl: AlarmRepositoryImp
    ): AlarmRepository
}