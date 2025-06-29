package com.developersphere.clock.presentation.screens.alarm_screen

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developersphere.clock.domain.AlarmUsecase
import com.developersphere.clock.domain.enum.Day
import com.developersphere.clock.domain.enum.Period
import com.developersphere.clock.domain.model.AlarmDataEntity
import com.developersphere.clock.utils.AlarmScheduler
import com.developersphere.clock.utils.TimeConversion
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
* TODO -
*  handle alarm on/off
*  handle alarm days
*  handle alarm title
*  handle alarm time
*  handle alarm sound
*  handle alarm vibrate
*  handle alarm repeat
*  handle alarm description
* */

@HiltViewModel
@RequiresApi(Build.VERSION_CODES.O)
class AlarmScreenViewModel @Inject constructor(
    private val alarmScheduler: AlarmScheduler,
    private val alarmUsecase: AlarmUsecase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(AlarmScreenUiState())
    var uiState: StateFlow<AlarmScreenUiState> = _uiState

    init {
        viewModelScope.launch {
            launch {
                alarmUsecase.getAllAlarm().collectLatest { alarmList ->
                    _uiState.update { currentState ->
                        currentState.copy(
                            alarms = alarmList
                        )
                    }
                }
            }
            Log.d("Clock", "Ra1 Alarm : ${_uiState.value.alarms}")

            launch {
                while (isActive) {
                    delay(1000)
                    _uiState.update { currentState ->
                        currentState.copy(
                            currentTime = TimeConversion.getCurrentFormattedTime()
                        )
                    }
                }
            }
        }
    }

    fun getAlarmById(alarmId: Int?): AlarmDataEntity? {
        if (alarmId != null) {
            _uiState.update { currentState ->
                currentState.copy(
                    currentAlarmId = alarmId
                )
            }
            return _uiState.value.alarms.firstOrNull { it.alarmId == alarmId }
        }
        return null
    }

    fun updateSelectedHour(hour: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedHour = hour
            )
        }
    }

    fun updateSelectedMinute(minute: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedMinute = minute

            )
        }
    }

    fun updateSelectedPeriod(selectedPeriod: Period) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedPeriod = selectedPeriod
            )
        }
    }

    fun scheduleAlarm() {
        if (_uiState.value.currentAlarmId == null) {
            createNewAlarm()
        } else {
            scheduleAlarm(_uiState.value.alarms[_uiState.value.currentAlarmId!!])
        }
        saveAlarm()
    }

    fun scheduleAlarm(alarm: AlarmDataEntity) {
        alarmScheduler.schedule(_uiState.value.alarms[_uiState.value.currentAlarmId!!])
    }

    fun cancelAlarm(alarmId: Int) {
        alarmScheduler.cancel(_uiState.value.alarms[alarmId])
    }

    private fun createNewAlarm() {
        val hour =
            if (_uiState.value.selectedPeriod == Period.PM && _uiState.value.selectedHour < 12) _uiState.value.selectedHour + 12 else _uiState.value.selectedHour

        // schedule alarm
        alarmScheduler.schedule(
            AlarmDataEntity(
                alarmId = null,
                alarmTime = TimeConversion.convertIntoLocalDateTime(
                    hour, _uiState.value.selectedMinute
                ),
                title = _uiState.value.alarmTitle,
                onDay = _uiState.value.alarmOnDays,
                isActive = true,
            )
        )
    }

    // save to Database.
    private fun saveAlarm() {
        viewModelScope.launch {
            alarmUsecase.addAlarm(
                alarmEntity = AlarmDataEntity(
                    alarmId = _uiState.value.currentAlarmId,
                    title = _uiState.value.alarmTitle,
                    alarmTime = TimeConversion.convertIntoLocalDateTime(
                        _uiState.value.selectedHour, _uiState.value.selectedMinute
                    ),
                    onDay = _uiState.value.alarmOnDays,
                    isActive = true,
                )
            )
        }
    }

    // delete from Database.
    fun deleteAlarm(
        title: String,
        hour: Int, minute: Int,
        onDay: Map<Day, Boolean>,
        isActive: Boolean,
    ) {
        viewModelScope.launch {
            alarmUsecase.deleteAlarm(
                alarmEntity = AlarmDataEntity(
                    title = title,
                    alarmTime = TimeConversion.convertIntoLocalDateTime(
                        hour, minute
                    ),
                    onDay = onDay,
                    isActive = isActive,
                )
            )
        }
    }

}