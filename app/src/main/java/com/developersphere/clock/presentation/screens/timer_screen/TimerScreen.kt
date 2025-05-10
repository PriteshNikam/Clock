package com.developersphere.clock.presentation.screens.timer_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.developersphere.clock.presentation.common_compose.CommonText
import com.developersphere.clock.presentation.common_compose.CustomButton
import com.developersphere.clock.presentation.common_compose.NormalDigitPicker
import com.developersphere.clock.ui.theme.BackGroundColor
import com.developersphere.clock.ui.theme.Green
import com.developersphere.clock.ui.theme.LightGrey
import com.developersphere.clock.ui.theme.White

@Composable
fun TimerScreen() {
    val hourList = (0..99).toList()
    val minuteList = (0..59).toList()
    val secondsList = (0..59).toList()

    var timerHour by rememberSaveable { mutableIntStateOf(0) }
    var timerMinute by rememberSaveable { mutableIntStateOf(15) }
    var timerSeconds by rememberSaveable { mutableIntStateOf(0) }

    val validTimer by remember(timerHour, timerMinute, timerSeconds) {
        derivedStateOf {
            timerHour > 0 || timerMinute > 0 || timerSeconds > 0
        }
    }

    Column(
        Modifier
            .background(color = BackGroundColor)
            .fillMaxSize()
            .padding(
                vertical = 24.dp
            ),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TimePicker(
            hourList = hourList,
            minuteList = minuteList,
            secondsList = secondsList,
            timerHour = timerHour,
            timerMinute = timerMinute,
            timerSeconds = timerSeconds,
            updateTimerHour = {
                timerHour = it
            },
            updateTimerMinute = {
                timerMinute = it
            },
            updateTimerSeconds = {
                timerSeconds = it
            },
        )

        // start button
        CustomButton(
            action = {},
            buttonTitle = "Start",
            buttonColor = if (validTimer) Green else LightGrey
        )
    }
}

@Composable
fun TimePicker(
    hourList: List<Int>,
    minuteList: List<Int>,
    secondsList: List<Int>,
    updateTimerHour: (hour: Int) -> Unit,
    updateTimerMinute: (minute: Int) -> Unit,
    updateTimerSeconds: (seconds: Int) -> Unit,
    timerHour: Int,
    timerMinute: Int,
    timerSeconds: Int,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        //hour picker
        NormalDigitPicker(
            numberList = hourList,
            defaultSelectedDigit = timerHour,
            label = "Hours",
            updateSelectedDigit = {
                updateTimerHour(it)
            }
        )

        Box(modifier = Modifier.padding(top = 40.dp)) {
            CommonText(
                ":",
                textStyle = TextStyle(
                    fontSize = 44.sp,
                    color = White,
                    fontWeight = FontWeight.Bold
                )
            )
        }

        // minute picker
        NormalDigitPicker(
            numberList = minuteList,
            defaultSelectedDigit = timerMinute,
            label = "Minutes",
            updateSelectedDigit = {
                updateTimerMinute(it)
            })

        Box(modifier = Modifier.padding(top = 40.dp)) {
            CommonText(
                ":",
                textStyle = TextStyle(
                    fontSize = 44.sp,
                    color = White,
                    fontWeight = FontWeight.Bold
                )
            )
        }

        // seconds picker
        NormalDigitPicker(
            numberList = secondsList,
            defaultSelectedDigit = timerSeconds,
            label = "Seconds",
            updateSelectedDigit = {
                updateTimerSeconds(it)
            })
    }

}
