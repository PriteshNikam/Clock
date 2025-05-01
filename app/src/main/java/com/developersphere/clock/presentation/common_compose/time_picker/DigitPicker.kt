package com.developersphere.clock.presentation.common_compose.time_picker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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
fun DigitPicker(
    circularList: List<Int>,
    initialValue: Int,
    onHourSelected: (Int) -> Unit = {},
    maxRange: Int,
) {
    val centerIndex = circularList.size / 2
    val subList = circularList.subList(centerIndex - maxRange, centerIndex + maxRange)
    val localIndex = subList.indexOf(initialValue)

    // -1 as we are highlighting center item where we add +1 condition.
    // here (centerIndex - 6) is starting index of sublist in repeatedHours
    val initialIndex = localIndex + (centerIndex - maxRange) - 1

    val listState = rememberLazyListState(initialFirstVisibleItemIndex = initialIndex)

    LaunchedEffect(listState.isScrollInProgress.not()) {
        val centerItemIndex = listState.firstVisibleItemIndex + 1
        val selectedHour = circularList.getOrNull(centerItemIndex) ?: 1
        onHourSelected(selectedHour)
    }

    val centerItemIndex by remember {
        derivedStateOf { listState.firstVisibleItemIndex + 1 }
    }

    Box(
        modifier = Modifier
            .height(160.dp)
            .width(52.dp), contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            state = listState,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            itemsIndexed(circularList) { index, item ->
                Box(modifier = Modifier.padding(vertical = 6.dp)) {
                    CommonText(
                        text = item.toString(),
                        textStyle = if (index == centerItemIndex)
                            TextStyle(
                                fontSize = 44.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Green
                            )
                        else
                            TextStyle(
                                fontSize = 34.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                    )
                }
            }
        }
    }
}