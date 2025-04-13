package com.developersphere.clock.presentation.screens.alarm_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.lazy.items
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
import com.developersphere.clock.presentation.common_compose.AlarmCardWidget
import com.developersphere.clock.presentation.common_compose.CommonText
import com.developersphere.clock.presentation.navigation.routes.AlarmRoute
import com.developersphere.clock.utils.DummyData.alarmScreenData

@Composable
fun AlarmScreen(navigation: (screen: AlarmRoute) -> Unit) {

    val alarmScreenViewModel = AlarmScreenViewModel()

    val currentTime by
    alarmScreenViewModel.currentTime.collectAsState()

    LazyColumn(
        Modifier
            .background(color = Color(0xff282F35))
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
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

        item {
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


        /**
         * this is not good for large list as this build all items
         * */
        item {
            CommonText(
                text = "Grid item with for loop",
                textStyle = TextStyle(fontSize = 24.sp, color = Color.Green)
            )
        }
        val cols = 2
        items(alarmScreenData.chunked(cols)) { alarm ->
            Row(modifier = Modifier.background(color = Color.Red)) {
                for (item in alarm) {
                    Box(modifier = Modifier.weight(1f)) {
                        AlarmCardWidget(item)
                    }
                }
            }
        }

        /**
         * here we gave max height
         */
        item {
            CommonText(
                text = "Grid item with LazyVerticalGrid & height",
                textStyle = TextStyle(fontSize = 24.sp, color = Color.Green)
            )
        }
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

@Preview
@Composable
fun PreviewAlarmScreen() {
    AlarmScreen {}
}
