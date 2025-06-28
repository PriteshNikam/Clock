package com.developersphere.clock.presentation.screens.alarm_screen

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developersphere.clock.domain.enum.Period
import com.developersphere.clock.domain.model.AlarmData
import com.developersphere.clock.utils.AlarmScheduler
import com.developersphere.clock.utils.DummyData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
@RequiresApi(Build.VERSION_CODES.O)
class AlarmScreenViewModel @Inject constructor(private val alarmScheduler: AlarmScheduler) :
    ViewModel() {

    private val _currentTime = MutableStateFlow(getCurrentFormattedTime())

    val currentTime: MutableStateFlow<String> = _currentTime

    private val currentAlarmId = MutableStateFlow<Int?>(null)

    private val _selectedHour = MutableStateFlow(6)

    val selectedHour = _selectedHour.value

    private val _selectedMinute = MutableStateFlow(0)

    val selectedMinute = _selectedMinute.value

    private val _selectedPeriod = MutableStateFlow(Period.AM.period)

    val selectedPeriod = _selectedPeriod.value

    private val _alarms = MutableStateFlow<List<AlarmData>>(DummyData.alarmScreenData)
    val alarms: StateFlow<List<AlarmData>> = _alarms

    init {
        viewModelScope.launch {
            while (isActive) {
                delay(1000)
                _currentTime.value = getCurrentFormattedTime()
            }
        }
    }

    fun getAlarmById(alarmId: Int?): AlarmData? {
        return _alarms.value.firstOrNull { it.alarmId == alarmId }
    }

    private fun getCurrentFormattedTime(): String {
        val formatter =
            DateTimeFormatter.ofPattern("hh:mm:ss a")
        return LocalTime.now().format(formatter)
    }

    fun updateSelectedHour(hour: Int) {
        Log.d("Clock", "Ra1 Hour : $hour")
        _selectedHour.value = hour
    }

    fun updateSelectedMinute(minute: Int) {
        Log.d("Clock", "Ra1 Minute : $minute")
        _selectedMinute.value = minute
    }

    fun scheduleAlarm() {
        if(currentAlarmId.value == null){
            createNewAlarm()
        }else {
            alarmScheduler.schedule(_alarms.value[currentAlarmId.value!!])
        }
    }

    fun cancelAlarm(alarmId: Int) {
        alarmScheduler.cancel(_alarms.value[alarmId])
    }

    private fun createNewAlarm() {
        val hour = if (_selectedPeriod.value == Period.PM.period && _selectedHour.value < 12)
            _selectedHour.value + 12 else _selectedHour.value

        saveAlarmTODB(
            _selectedHour.value,
            _selectedMinute.value,
            _selectedPeriod.value
        )

        alarmScheduler.schedule(
            AlarmData(
                alarmId = null,
                alarmTime = LocalDateTime.of(
                    LocalDate.now(),
                    LocalTime.of(hour, _selectedMinute.value),
                ),
                title = "My alarm",
            )
        )
    }

    private fun saveAlarmTODB(value: Int, value1: Int, value2: String) {
        // TODO: save alarm to db
    }
}