package com.developersphere.clock.presentation.screens.add_alarm_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.developersphere.clock.domain.enum.Period
import com.developersphere.clock.domain.model.AlarmData
import com.developersphere.clock.presentation.common_compose.*
import com.developersphere.clock.presentation.navigation.routes.AlarmRoute
import com.developersphere.clock.presentation.screens.alarm_screen.AlarmScreenViewModel
import com.developersphere.clock.ui.theme.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddAlarmScreen(
    alarmId: Int?,
    navigation: (destination: AlarmRoute?) -> Unit,
    viewModel: AlarmScreenViewModel = hiltViewModel(),
) {
    val hourList = (0..99).toList()
    val minuteList = (0..59).toList()

    var hour = rememberSaveable { viewModel.selectedHour }
    var minute = rememberSaveable { viewModel.selectedMinute }
    var period = rememberSaveable { viewModel.selectedPeriod }

    val alarm = remember { mutableStateOf(AlarmData()) }

    LaunchedEffect(alarm.value.alarmId) {
        alarm.value = viewModel.getAlarmById(alarmId) ?: AlarmData()
        hour = alarm.value.alarmTime?.hour ?: 0
        minute = alarm.value.alarmTime?.minute ?: 0
        period = if (hour < 12) Period.AM.period else Period.PM.period
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackGroundColor)
            .padding(bottom = 12.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(), horizontalArrangement = Arrangement.Start
        ) {
            // back icon
            Icon(
                modifier = Modifier
                    .size(42.dp)
                    .clickable {
                        navigation.invoke(null)
                    },
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = null,
                tint = Green,
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // hour picker
            NormalDigitPicker(
                numberList = hourList,
                defaultSelectedDigit = hour,
                updateSelectedDigit = {
                    viewModel.updateSelectedHour(it)
                }
            )

            CommonText(
                ":",
                textStyle = TextStyle(
                    fontSize = 44.sp,
                    color = White,
                    fontWeight = FontWeight.Bold
                )
            )

            // minute picker
            NormalDigitPicker(
                numberList = minuteList,
                defaultSelectedDigit = minute,
                updateSelectedDigit = {
                    viewModel.updateSelectedMinute(it)
                }
            )

            PeriodPicker(
                selectedPeriod = period,
                onPeriodSelected = {
                    // updateTimerPeriod(it)
                }
            )
        }

        AlarmDetail(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomButton(
                action = {},
                buttonTitle = "Cancel",
                buttonColor = BottomAppBarBackgroundColor
            )

            CustomButton(
                action = {
                    viewModel.scheduleAlarm();
                },
                buttonTitle = "Save",
                buttonColor = BottomAppBarBackgroundColor
            )
        }
    }
}

@Composable
fun AlarmDetail(modifier: Modifier = Modifier) {

    var alarmTitle by rememberSaveable { mutableStateOf("") }

    Surface(
        modifier = modifier
            .padding(
                vertical = 24.dp
            ),
        color = BottomAppBarBackgroundColor,
        shape = CircleShape.copy(
            all = CornerSize(28.dp)
        )
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(horizontal = 24.dp)
        ) {
            CommonText("Calendar")
            AlarmDaysWidget()
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = alarmTitle,
                onValueChange = {
                    alarmTitle = it
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                textStyle = TextStyle(color = White, fontSize = 16.sp),
                placeholder = {
                    (Text(
                        "Alarm name",
                        style = TextStyle(color = LightGrey, fontSize = 16.sp)
                    ))
                },
                colors = TextFieldDefaults.colors(
                    cursorColor = Green,
                    unfocusedContainerColor = BottomAppBarBackgroundColor,
                    focusedContainerColor = BottomAppBarBackgroundColor,
                    focusedTextColor = White,
                    unfocusedTextColor = White,
                    focusedIndicatorColor = Transparent,
                    unfocusedIndicatorColor = Transparent,

                    )
                //  label = {Text("Alarm Title")}
            )

            BasicTextField(
                value = alarmTitle,
                onValueChange = {
                    alarmTitle = it
                },
                cursorBrush = SolidColor(White),
                textStyle = TextStyle(color = White, fontSize = 16.sp),
            )
            CommonText("Calendar")
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun PreviewCreateAlarmScreen() {
    AddAlarmScreen(
        0,
        navigation = {}
    )
}