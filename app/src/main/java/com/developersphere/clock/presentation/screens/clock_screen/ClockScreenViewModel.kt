package com.developersphere.clock.presentation.screens.clock_screen

import android.os.Build
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class ClockScreenViewModel @Inject constructor():ViewModel() {
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

    private fun getFormattedTime(): String {
        val formatter = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            DateTimeFormatter.ofPattern("hh:mm:ss a")
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        return LocalTime.now().format(formatter)
    }
}