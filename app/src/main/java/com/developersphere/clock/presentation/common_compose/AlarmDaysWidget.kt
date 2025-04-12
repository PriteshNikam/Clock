package com.developersphere.clock.presentation.common_compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.developersphere.clock.domain.enum.Days

@Composable
fun AlarmDaysWidget(onDays: MutableMap<Days, Boolean>?) {
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
