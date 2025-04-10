package com.developersphere.clock.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.developersphere.clock.presentation.common_compose.AlarmCardWidget
import com.developersphere.clock.presentation.common_compose.CommonText
import com.developersphere.clock.utils.DummyData.alarmScreenData

@Composable
fun AlarmScreen() {
    Column(
        Modifier
            .background(color = Color(0xff282F35)),
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
        ) {
            item(span = { GridItemSpan(maxCurrentLineSpan) }) {
                CommonText(text = "8:00 AM",
                    textStyle = TextStyle(fontSize = 44.sp, color = Color.White),modifier = Modifier.padding(horizontal = 8.dp))
            }

            item(span = { GridItemSpan(maxCurrentLineSpan) }) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .height(24.dp)
                        .align(Alignment.End),
                    horizontalArrangement = Arrangement.End
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Add,
                        tint = Color.White,
                        contentDescription = "",
                        modifier = Modifier.size(24.dp)
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

            items(alarmScreenData) { item ->
                AlarmCardWidget(item)
            }
        }
    }
}

@Preview
@Composable
fun PreviewAlarmScreen() {
    AlarmScreen()
}
