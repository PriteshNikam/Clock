package com.developersphere.clock.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.developersphere.clock.domain.enum.Days
import com.developersphere.clock.domain.model.AlarmData
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
            AlarmItemWidget(item)
        }
    }
}

@Composable
fun AlarmItemWidget(item: AlarmData) {
    val context = LocalContext.current
    ElevatedCard(
        modifier = Modifier.padding(9.dp),
        shape = CardDefaults.elevatedShape,
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color(0xff363E46)
        )
    ) {
        var checkState by remember {
            mutableStateOf(item.isActive ?: false)
        }
        Column(modifier = Modifier.padding(vertical = 18.dp, horizontal = 24.dp)) {
            CommonText(
                text = item.title ?: "",
                textStyle = TextStyle(color = Color.White, fontSize = 14.sp)
            )
            CommonText(
                text = item.time ?: "",
                textStyle = TextStyle(
                    color = Color.White,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Medium
                )
            )
            WeekDaysWidget(onDays = item.onDays)
            Spacer(modifier = Modifier.height(24.dp))
            Switch(
                modifier = Modifier
                    .width(40.dp)
                    .height(24.dp)
                    .align(Alignment.End),
                checked = checkState,//item.isActive ?: false,
                onCheckedChange = {
                    Toast.makeText(context, "${item.isActive}", Toast.LENGTH_SHORT).show()
                    // item.isActive = !(item.isActive ?: false)
                    checkState = it
                },
                colors = SwitchDefaults.colors(
                    checkedTrackColor = Color.Green
                )
            )

        }
    }
}

@Composable
fun WeekDaysWidget(onDays: MutableMap<Days, Boolean>?) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        onDays?.forEach { (key, value) ->
            CommonText(
                text = key.code,
                textStyle = TextStyle(
                    fontSize = 14.sp,
                    color = if (!value) Color.White else Color.Green,
                    fontWeight = FontWeight.Bold
                ),
            )
        }
    }
}

@Preview
@Composable
fun PreviewAlarmScreen() {
    AlarmScreen()
}
