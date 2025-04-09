package com.developersphere.clock.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.developersphere.clock.domain.enum.Days
import com.developersphere.clock.presentation.common_compose.AlarmCardWidget
import com.developersphere.clock.presentation.common_compose.CommonText
import com.developersphere.clock.utils.DummyData.alarmScreenData

@Composable
fun AlarmScreen() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xff282F35)),
        contentPadding = PaddingValues(9.dp)
    ) {
        items(alarmScreenData) { item ->
            AlarmCardWidget(item)
        }
    }
}
@Preview
@Composable
fun PreviewAlarmScreen() {
    AlarmScreen()
}
