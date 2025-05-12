package com.developersphere.clock.presentation.screens.timer_screen

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.developersphere.clock.presentation.common_compose.CommonText
import com.developersphere.clock.presentation.common_compose.CustomButton
import com.developersphere.clock.presentation.common_compose.NormalDigitPicker
import com.developersphere.clock.ui.theme.BackGroundColor
import com.developersphere.clock.ui.theme.Green
import com.developersphere.clock.ui.theme.LightGrey
import com.developersphere.clock.ui.theme.Red
import com.developersphere.clock.ui.theme.White

@Composable
fun TimerScreen(viewModel: TimerViewModel = hiltViewModel()) {

    val isRunning = viewModel.isRunning.collectAsState().value
    val timerHour = viewModel.hour.collectAsState().value
    val timerMinute = viewModel.minute.collectAsState().value
    val timerSeconds = viewModel.second.collectAsState().value
    val timer = viewModel.timer.collectAsState().value

    val hourList = (0..99).toList()
    val minuteList = (0..59).toList()
    val secondsList = (0..59).toList()

    val validTimer by remember(timerHour, timerMinute, timerSeconds) {
        derivedStateOf {
            timerHour > 0 || timerMinute > 0 || timerSeconds > 0
        }
    }

    var progress by rememberSaveable { mutableStateOf(false) }

    val animationProgress = animateFloatAsState(
        targetValue = if (progress) 0f else 1f,
        animationSpec = TweenSpec(10000, easing = FastOutSlowInEasing),
        finishedListener = { progress = false },
        label = "circularProgress"
    )

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
        if (!isRunning) {
            TimePicker(
                hourList = hourList,
                minuteList = minuteList,
                secondsList = secondsList,
                timerHour = timerHour,
                timerMinute = timerMinute,
                timerSeconds = timerSeconds,
                updateTimerHour = {
                    viewModel.setHour(it)
                },
                updateTimerMinute = {
                    viewModel.setMinute(it)
                },
                updateTimerSeconds = {
                    viewModel.setSecond(it)
                },
            )
        }

        if (isRunning) {
            Surface(
                modifier = Modifier
                    .size(340.dp)
                    .align(Alignment.CenterHorizontally),
                shape = CircleShape,
                border = BorderStroke(2.dp, Green),
                color = BackGroundColor,
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    progress = animationProgress.value
                )

                Box(contentAlignment = Alignment.Center) {
                    CommonText(
                        timer, textStyle = TextStyle(
                            letterSpacing = 12.sp,
                            fontSize = 40.sp,
                            color = if (isRunning) Green else White,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }
            }
        }

        // start button
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
                CustomButton(
                    action = {
                        progress = true
                        viewModel.resetTimer()
                    },
                    buttonTitle = "Reset",
                    buttonColor = if (validTimer) Green else LightGrey
                )


            CustomButton(
                action = {
                    progress = true
                    if(isRunning)
                        viewModel.stopTimer()
                        else
                    viewModel.startTimer()
                },
                buttonTitle = if (isRunning) "Pause" else "Start",
                buttonColor = if (validTimer && !isRunning) Green
                else if (isRunning) Red
                else LightGrey
            )
        }
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

@Preview
@Composable
fun TimerScreenPreview() {
    TimerScreen()
}
