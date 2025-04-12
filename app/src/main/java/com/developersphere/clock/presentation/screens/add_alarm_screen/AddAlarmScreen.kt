package com.developersphere.clock.presentation.screens.add_alarm_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.developersphere.clock.presentation.common_compose.CommonText
import com.developersphere.clock.presentation.navigation.routes.AlarmRoute

@Composable
fun AddAlarmScreen(
    alarmId: Int, alarmTitle: String,
    navigation:(destination: AlarmRoute?) -> Unit,
) {
    Scaffold {
        Column(
            modifier = Modifier
                .padding(paddingValues = it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CommonText(text = "Add Alarm BottomNavRoute", textStyle = TextStyle(fontSize = 24.sp))
            Spacer(modifier = Modifier.height(24.dp))
            CommonText(text = "Alarm id :: $alarmId", textStyle = TextStyle(fontSize = 16.sp))
            CommonText(text = "Alarm :: $alarmTitle", textStyle = TextStyle(fontSize = 16.sp))
            Spacer(Modifier.height(24.dp))
            Button(onClick = {
                navigation(null)
            }) {
                CommonText(text = "go back")
            }

            Button(onClick = {
                navigation(AlarmRoute.DummyScreen)
            }) {
                CommonText(text = "go to dummy screen")
            }
        }
    }
}


@Preview
@Composable
fun PreviewCreateAlarmScreen() {
    AddAlarmScreen(0, "") {}
}