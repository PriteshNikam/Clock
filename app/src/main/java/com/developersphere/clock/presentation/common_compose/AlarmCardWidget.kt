package com.developersphere.clock.presentation.common_compose

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.developersphere.clock.domain.model.AlarmData
import com.developersphere.clock.presentation.navigation.routes.AlarmRoute
import com.developersphere.clock.utils.DummyData.alarmScreenData
import com.developersphere.clock.utils.StringFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AlarmCardWidget(item: AlarmData, navigation: (screen: AlarmRoute) -> Unit) {
    ElevatedCard(
        modifier = Modifier
            .padding(8.dp)
            .shadow(5.dp)
            .clickable {
                navigation.invoke(
                    AlarmRoute.AddAlarmScreen(
                        alarmId = item.alarmId,
                    )
                )
            },
        shape = CardDefaults.elevatedShape,
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color(0xff363E46)
        )
    ) {
        var checkState by remember {
            mutableStateOf(item.isActive ?: false)
        }
        Column(
            modifier = Modifier
                .padding(vertical = 18.dp, horizontal = 24.dp)
        ) {
            CommonText(
                text = item.title ?: "",
                textStyle = TextStyle(color = Color.White, fontSize = 14.sp)
            )
            CommonText(
                text = (StringFormatter.formatLocalTimeIntoHourMinute(item.alarmTime.toString())),
                textStyle = TextStyle(
                    color = Color.White,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Medium
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            AlarmDaysWidget(onDay = item.onDay)
            Spacer(modifier = Modifier.height(16.dp))
            Switch(
                modifier = Modifier
                    .align(Alignment.End)
                    .scale(0.9f),
                checked = checkState,
                onCheckedChange = {
                    checkState = it
                },
                colors = SwitchDefaults.colors(
                    checkedTrackColor = Color.Green
                )
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun PreviewAlarmCard() {
    AlarmCardWidget(item = alarmScreenData.first(), navigation = {})
}