package com.developersphere.clock.presentation.screens.timer_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TimerViewModel @Inject constructor() : ViewModel() {
    private val _timerHour = MutableStateFlow(0)
    val hour: StateFlow<Int> = _timerHour

    fun setHour(value: Int) {
        _timerHour.value = value
    }

    private val _timerMinute = MutableStateFlow(15)
    val minute: StateFlow<Int> = _timerMinute

    fun setMinute(value: Int) {
        _timerMinute.value = value
    }

    private val _timerSecond = MutableStateFlow(0)
    val second: StateFlow<Int> = _timerSecond

    fun setSecond(value: Int) {
        _timerSecond.value = value
    }

    private val _isRunning = MutableStateFlow<Boolean>(false)
    var isRunning: StateFlow<Boolean> = _isRunning

    // total time.
    private var totalTime =
        (_timerHour.value * 3600) + (_timerMinute.value * 60) + _timerSecond.value

    private var _timer = MutableStateFlow("00:00:00")
    var timer: StateFlow<String> = _timer

    private var _job: Job? = null

    fun startTimer() {
        _isRunning.value = true
        _job = viewModelScope.launch {
            while (isActive) {

                _timer.value = convertSecondsToTimeStringFormat(totalTime)

                Log.d("Clock", "Ra1 timer :: ${_timer.value}")

                totalTime -= 1
                delay(1000)
            }
        }
    }

    fun stopTimer() {
        _job?.cancel()
    }

    fun resetTimer() {
        _job?.cancel()
        _isRunning.value = false
        _timerHour.value = 0
        _timerMinute.value = 15
        _timerSecond.value = 0
    }

    private fun convertSecondsToTimeStringFormat(totalSeconds: Int): String {
        val hour = (totalSeconds / 3600).toString().padStart(2, '0')
        val minute = ((totalSeconds % 3600) / 60).toString().padStart(2, '0')
        val seconds = (totalSeconds % 60).toString().padStart(2, '0')
        return "${hour}:${minute}:${seconds}"
    }

}