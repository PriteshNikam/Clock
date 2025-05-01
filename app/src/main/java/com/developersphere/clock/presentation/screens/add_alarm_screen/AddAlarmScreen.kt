package com.developersphere.clock.presentation.screens.add_alarm_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.developersphere.clock.presentation.common_compose.AlarmDaysWidget
import com.developersphere.clock.presentation.common_compose.CommonText
import com.developersphere.clock.presentation.common_compose.time_picker.TimePicker
import com.developersphere.clock.presentation.navigation.routes.AlarmRoute
import com.developersphere.clock.ui.theme.*

@Composable
fun AddAlarmScreen(
    alarmId: Int, alarmTitle: String,
    navigation: (destination: AlarmRoute?) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackGroundColor)
            .padding(
                vertical = 24.dp
            ),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        TimePicker()
        AlarmDetail()
    }
}

@Composable
fun AlarmDetail() {

    var alarmTitle by rememberSaveable { mutableStateOf("") }

    Surface(
        modifier = Modifier
            .fillMaxSize()
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
                } ,
                cursorBrush = SolidColor(White),
                textStyle = TextStyle(color = White, fontSize = 16.sp),
            )
            CommonText("Calendar")
        }
    }
}

@Preview
@Composable
fun PreviewCreateAlarmScreen() {
    AddAlarmScreen(0, "") {}
}