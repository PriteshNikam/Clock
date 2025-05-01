package com.developersphere.clock.presentation.common_compose.time_picker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.developersphere.clock.presentation.common_compose.CommonText

@Composable
fun TimePicker() {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val hours = (1..12).toList()
        val minutes = (0..59).toList()
        val repeatedHours = List(1000) { hours[it % 12] }
        val repeatMinutes = List(1000) { minutes[it % 60] }

        DigitPicker(
            circularList = repeatedHours,
            initialValue = 6,
            maxRange = 12,
        )
        CommonText(
            ":", textStyle = TextStyle(
                fontSize = 44.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        )
        DigitPicker(
            circularList = repeatMinutes,
            initialValue = 0,
            maxRange = 59,
        )
        TimePeriodPicker(
            updateTimePicker = {}
        )
    }
}

enum class TimePeriod(val period: String) {
    AM(period = "am"),
    PM(period = "pm")
}

@Composable
fun TimePeriodPicker(updateTimePicker: (String) -> Unit = {}) {

    val timePeriods = TimePeriod.entries
    val listState: LazyListState = rememberLazyListState(initialFirstVisibleItemIndex = 0)

    LaunchedEffect(listState.isScrollInProgress.not()) {
        val currentIndex = listState.firstVisibleItemIndex
        val currentTimerPeriod = timePeriods.getOrNull(currentIndex)
        updateTimePicker((currentTimerPeriod?.period ?: timePeriods.first().period))
    }

    val selectedTimePeriod by remember {
        derivedStateOf {
            timePeriods[listState.firstVisibleItemIndex]
        }
    }

    Box(
        Modifier
            .height(160.dp)
            .width(52.dp),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(state = listState) {
            itemsIndexed(timePeriods) { index, item ->
                Box(modifier = Modifier.padding(vertical = 6.dp)) {
                    CommonText(
                        item.period,
                        textStyle = if (item == selectedTimePeriod) TextStyle(
                            fontSize = 36.sp,
                            color = Color.Green,
                            fontWeight = FontWeight.Bold

                        )
                        else
                            TextStyle(
                                fontSize = 24.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                    )
                }
            }
        }
    }
}
