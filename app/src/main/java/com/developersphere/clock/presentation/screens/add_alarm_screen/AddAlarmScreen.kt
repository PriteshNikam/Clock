package com.developersphere.clock.presentation.screens.add_alarm_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.developersphere.clock.presentation.common_compose.time_picker.TimePicker
import com.developersphere.clock.presentation.navigation.routes.AlarmRoute

@Composable
fun AddAlarmScreen(
    alarmId: Int, alarmTitle: String,
    navigation: (destination: AlarmRoute?) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xff282F35)),
        verticalArrangement = Arrangement.Center,
    ) {
        TimePicker()
    }
}


@Preview
@Composable
fun PreviewCreateAlarmScreen() {
    AddAlarmScreen(0, "") {}
}