package com.developersphere.clock.presentation.screens.alarm_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
class AlarmScreenViewModel: ViewModel() {

    @RequiresApi(Build.VERSION_CODES.O)
    private val _currentTime = MutableStateFlow(getFormattedTime())

    val currentTime: MutableStateFlow<String> = _currentTime

    init {
        viewModelScope.launch {
            while(isActive) {
                delay(1000)
                _currentTime.value = getFormattedTime()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getFormattedTime(): String {
        val formatter = DateTimeFormatter.ofPattern("hh:mm:s a")
        return LocalTime.now().format(formatter)
    }
}