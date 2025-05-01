package com.developersphere.clock.presentation.screens.alarm_screen

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
import androidx.compose.ui.graphics.Color
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
import com.developersphere.clock.utils.DummyData.alarmScreenData

@Composable
fun AlarmScreen(
    alarmScreenViewModel: AlarmScreenViewModel = hiltViewModel(),
    navigation: (screen: AlarmRoute) -> Unit,
) {

    val currentTime by
    alarmScreenViewModel.currentTime.collectAsState()

    LazyColumn(
        Modifier
            .background(color = Color(0xff282F35))
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
                items(alarmScreenData) { alarm ->
                    AlarmCardWidget(alarm)
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
                color = Color.White,
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
            tint = Color.White,
            contentDescription = "",
            modifier = Modifier
                .size(24.dp)
                .clickable {
                    navigation(
                        AlarmRoute.AddAlarmScreen(
                            alarmId = 555,
                            alarmTitle = "Dummy alarm"
                        )
                    )
                }
        )
        Spacer(modifier = Modifier.width(12.dp))
        Icon(
            imageVector = Icons.Default.MoreVert,
            tint = Color.White,
            contentDescription = "",
            modifier = Modifier.size(24.dp)
        )
    }
}

@Preview
@Composable
fun PreviewAlarmScreen() {
    AlarmScreen {}
}
