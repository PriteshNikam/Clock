package com.developersphere.clock.presentation.screens.alarm_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.developersphere.clock.presentation.common_compose.AlarmCardWidget
import com.developersphere.clock.presentation.common_compose.CommonText
import com.developersphere.clock.presentation.navigation.routes.AlarmRoute
import com.developersphere.clock.ui.theme.BackGroundColor
import com.developersphere.clock.ui.theme.White

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AlarmScreen(
    alarmScreenViewModel: AlarmScreenViewModel = hiltViewModel(),
    navigation: (screen: AlarmRoute) -> Unit,
) {

    val currentTime by
    alarmScreenViewModel.currentTime.collectAsState()

    val alarms = alarmScreenViewModel.alarms.collectAsState()

    LazyColumn(
        Modifier
            .background(color = BackGroundColor)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        //show current time
        item {
            CurrentTime(currentTime)
        }
        
        // header with add and more icon.
        item {
            AddAndMoreActions(navigation)
        }

        // list of added alarm
        item {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .heightIn(
                        min = 100.dp,
                        max = 1000.dp
                    ),
            ) {
                items(alarms.value) { alarm ->
                    AlarmCardWidget(alarm,navigation)
                }
            }
        }
    }
}

@Composable
fun CurrentTime(currentTime: String) {
        CommonText(
            text = currentTime,
            textStyle = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.Monospace,
                color = White,
                letterSpacing = 2.sp
            ),
            modifier = Modifier.padding(horizontal = 8.dp)
        )
}

@Composable
fun AddAndMoreActions(navigation: (screen: AlarmRoute) -> Unit) {
    Row(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .fillMaxWidth()
            .height(24.dp),
        horizontalArrangement = Arrangement.End
    ) {
        Icon(
            imageVector = Icons.Rounded.Add,
            tint = White,
            contentDescription = "",
            modifier = Modifier
                .size(24.dp)
                .clickable {
                    navigation(
                        AlarmRoute.AddAlarmScreen(alarmId = null)
                    )
                }
        )
        Spacer(modifier = Modifier.width(12.dp))
        Icon(
            imageVector = Icons.Default.MoreVert,
            tint = White,
            contentDescription = "",
            modifier = Modifier.size(24.dp)
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun PreviewAlarmScreen() {
    AlarmScreen {}
}
