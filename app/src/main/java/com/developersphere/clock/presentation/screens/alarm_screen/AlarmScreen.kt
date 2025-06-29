package com.developersphere.clock.presentation.screens.alarm_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.developersphere.clock.R
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

    val currentUIState = alarmScreenViewModel.uiState.collectAsState()

    LazyColumn(
        Modifier
            .background(color = BackGroundColor)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        userScrollEnabled = currentUIState.value.alarms.isNotEmpty()
    ) {
        //show current time
        item {
            CurrentTime(currentTime = currentUIState.value.currentTime)
        }

        // header with add and more icon.
        item {
            AddAndMoreActions(navigation)
        }

        // list of added alarm
        item {
            if (currentUIState.value.alarms.isEmpty()) {
                Box(
                    modifier = Modifier.fillParentMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    EmptyAlarmScreen()
                }
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .heightIn(
                            min = 100.dp,
                            max = 1000.dp
                        ),
                ) {
                    items(currentUIState.value.alarms) { alarm ->
                        AlarmCardWidget(alarm, navigation)
                    }
                }
            }
        }
    }
}

@Composable
fun EmptyAlarmScreen() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(R.drawable.alarm_clock),
            contentDescription = "alarm clock",
            modifier = Modifier.size(64.dp),
            tint = White
        )
        Spacer(modifier = Modifier.height(24.dp))
        CommonText(
            "No Alarm",
            textStyle = TextStyle(
                fontSize = 16.sp,
                color = White
            )
        )
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
