package com.developersphere.clock.presentation.screens.timer_screen

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

    private val _showTimer = MutableStateFlow(false)
    var showTimer: StateFlow<Boolean> = _showTimer

    private val _isRunning = MutableStateFlow(false)
    var isRunning: StateFlow<Boolean> = _isRunning

    private var _remainingMilliSeconds = MutableStateFlow(0)
    val remainingMilliSeconds:StateFlow<Int> = _remainingMilliSeconds

    private var _job: Job? = null

    fun startTimer() {
        _showTimer.value = true
        _isRunning.value = true
        _remainingMilliSeconds.value =  (_timerHour.value * 3600) + (_timerMinute.value * 60) + _timerSecond.value
        _job = viewModelScope.launch {
            while (isActive) {
                if(remainingMilliSeconds.value == 0)
                {
                   _showTimer.value = false
                    break
                }
                _remainingMilliSeconds.value -= 1
                delay(1000)
            }
        }
    }

    fun stopTimer() {
        _job?.cancel()
        _isRunning.value = false
    }

    fun resetTimer() {
        _job?.cancel()
        _showTimer.value = false
        _isRunning.value = false
        _timerHour.value = 0
        _timerMinute.value = 15
        _timerSecond.value = 0
    }

}