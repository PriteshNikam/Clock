package com.developersphere.clock.presentation.screens.timer_screen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.developersphere.clock.presentation.common_compose.*
import com.developersphere.clock.ui.theme.*
import com.developersphere.clock.utils.StringFormatter
import kotlinx.coroutines.*

@Composable
fun TimerScreen(viewModel: TimerViewModel = hiltViewModel()) {

    val showTimer = viewModel.showTimer.collectAsState().value
    val isRunning = viewModel.isRunning.collectAsState().value
    val timerHour = viewModel.hour.collectAsState().value
    val timerMinute = viewModel.minute.collectAsState().value
    val timerSeconds = viewModel.second.collectAsState().value
    val timer = viewModel.remainingMilliSeconds.collectAsState().value
    val totalTime = ((timerHour * 3600) + (timerMinute * 60) + timerSeconds).toFloat()

    val hourList = (0..99).toList()
    val minuteList = (0..59).toList()
    val secondsList = (0..59).toList()

    val validTimer by remember(timerHour, timerMinute, timerSeconds) {
        derivedStateOf {
            timerHour > 0 || timerMinute > 0 || timerSeconds > 0
        }
    }

    val sweepAnim = remember { Animatable(360f) }

    LaunchedEffect(showTimer) {
        if (showTimer && totalTime > 0) {

            while (isActive && viewModel.remainingMilliSeconds.value > 0) {
                val remaining = viewModel.remainingMilliSeconds.value.toFloat()
                val progress = remaining / totalTime
                sweepAnim.animateTo(progress * 360f,animationSpec = tween(50))
                delay(16) // roughly 60fps
            }
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
        if (!showTimer) {
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
        else{
            Box(contentAlignment = Alignment.Center) {
                Canvas(
                    modifier = Modifier
                        .size(350.dp)
                        .padding(4.dp)
                ) {
                    drawArc(
                        color = Red,
                        startAngle = 270f,
                        sweepAngle = sweepAnim.value,
                        useCenter = false,
                        style = Stroke(
                            width = 16f,
                        )
                    )
                }

                CommonText(
                    StringFormatter.formatInHourMinuteSeconds(timer),
                    textStyle = TextStyle(
                        letterSpacing = 12.sp,
                        fontSize = 40.sp,
                        color = Green,
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }
        }

        TimerButtons(viewModel,validTimer,isRunning)
    }
}

@Composable
fun TimerButtons(viewModel: TimerViewModel, validTimer: Boolean, isRunning: Boolean) {
    // start button
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CustomButton(
            enabled = validTimer || isRunning,
            action = {
                viewModel.resetTimer()
            },
            buttonTitle = "Reset",
            buttonColor =  BottomAppBarBackgroundColor
        )

        CustomButton(
            enabled = validTimer,
            action = {
                    if (isRunning)
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