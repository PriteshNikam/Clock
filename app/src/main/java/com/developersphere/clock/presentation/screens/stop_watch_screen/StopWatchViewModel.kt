package com.developersphere.clock.presentation.screens.stop_watch_screen

import android.os.SystemClock
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StopWatchViewModel @Inject constructor(): ViewModel() {

    private var timerJob: Job? = null
    private var startTime = 0L
    private val _isRunning = MutableStateFlow(false)
    var isRunning: StateFlow<Boolean> = _isRunning

    private val _stopWatchTime = MutableStateFlow(0L)
    val time = _stopWatchTime.asStateFlow()
    
    fun startStopWatch(){
        startTime = SystemClock.elapsedRealtime() - _stopWatchTime.value
        _isRunning.value = true

       timerJob =  viewModelScope.launch {
            while(isActive) {
                _stopWatchTime.value = SystemClock.elapsedRealtime() -  startTime
                delay(16L)
            }
        }
    }

    fun stopStopWatch(){
        timerJob?.cancel()
        _isRunning.value = false
    }

    fun resetStopWatch(){
        timerJob?.cancel()
        _stopWatchTime.value = 0L
        _isRunning.value = false
    }

}