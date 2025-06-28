package com.developersphere.clock.presentation.common_compose

import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.developersphere.clock.domain.enum.Period
import com.developersphere.clock.ui.theme.Green
import com.developersphere.clock.ui.theme.White
import kotlin.math.abs

@Composable
fun PeriodPicker(
    selectedPeriod: String,
    onPeriodSelected: (String) -> Unit,
) {
    val periods = listOf("", Period.AM.period, Period.PM.period,"")
    val listState = rememberLazyListState(
        initialFirstVisibleItemIndex = periods.indexOf(selectedPeriod).coerceAtLeast(0)
    )
    val flingBehavior = rememberSnapFlingBehavior(lazyListState = listState)

    val selectedIndex by remember {
        derivedStateOf {
            val layoutInfo = listState.layoutInfo
            val center = (layoutInfo.viewportStartOffset + layoutInfo.viewportEndOffset) / 2
            layoutInfo.visibleItemsInfo.minByOrNull { item ->
                abs(center - (item.offset + item.size / 2))
            }?.index?.rem(periods.size) ?: 0
        }
    }

    LaunchedEffect(selectedIndex) {
        onPeriodSelected(periods[selectedIndex])
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(Modifier.height(120.dp)) {
            LazyColumn(
                state = listState,
                flingBehavior = flingBehavior,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                items(periods.size) { index ->
                    val item = periods[index]
                    val isSelected = index == selectedIndex

                    CommonText(
                        text = item,
                        modifier = if (isSelected) Modifier else Modifier.alpha(0.5f),
                        textStyle = if (isSelected)
                            TextStyle(fontSize = 26.sp, fontWeight = FontWeight.Bold, color = Green)
                        else
                            TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold, color = White)
                    )
                }
            }
        }
    }
}