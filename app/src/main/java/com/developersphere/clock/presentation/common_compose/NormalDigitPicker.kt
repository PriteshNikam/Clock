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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.developersphere.clock.ui.theme.White
import kotlin.math.abs

@Composable
fun NormalDigitPicker(
    numberList: List<Int>,
    updateSelectedDigit: (selectedDigit: Int) -> Unit,
    defaultSelectedDigit: Int? = 0,
    label: String? = null,
) {
    val repeatCount = 100
    val totalItem = repeatCount * numberList.size

    val defaultIndexInList = numberList.indexOf(defaultSelectedDigit).takeIf { it != -1 } ?: 0
    val middleIndex = totalItem / 2
    val initialItemIndex = middleIndex - (middleIndex % numberList.size) + defaultIndexInList

    val listState = rememberLazyListState(initialFirstVisibleItemIndex = initialItemIndex)
    val flingBehavior = rememberSnapFlingBehavior(listState)

    // LaunchedEffect to trigger initial scroll if needed (though initialFirstVisibleItemIndex should handle it)
    LaunchedEffect(listState) {
        // Potentially scroll to the initial item again if the initial placement wasn't perfect
        if (defaultSelectedDigit != null) {
            val targetIndex = middleIndex - (middleIndex % numberList.size) + numberList.indexOf(
                defaultSelectedDigit
            )
            if (targetIndex in 1 until totalItem) {
                listState.scrollToItem(targetIndex - 1)
            }
        }
    }

    val selectedIndex by remember {
        derivedStateOf {
            val layoutInfo = listState.layoutInfo
            if (layoutInfo.visibleItemsInfo.isNotEmpty()) {
                val center = (layoutInfo.viewportStartOffset + layoutInfo.viewportEndOffset) / 2
                layoutInfo.visibleItemsInfo.minByOrNull { item ->
                    abs(center - (item.offset + item.size / 2))
                }?.index?.rem(numberList.size) ?: 0
            } else {
                0
            }
        }
    }

    LaunchedEffect(selectedIndex) {
        numberList.getOrNull(selectedIndex)?.let { updateSelectedDigit(it) }
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (!label.isNullOrEmpty()) {
            CommonText(
                modifier = Modifier.alpha(0.7f),
                text = label,
                textStyle = TextStyle(fontSize = 16.sp, color = White)
            )
        }
        Box(Modifier.height(160.dp)) {
            LazyColumn(
                state = listState,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(18.dp),
                flingBehavior = flingBehavior,
            ) {
                items(totalItem) { index ->
                    val item = numberList[index % numberList.size]
                    val isSelectedIndex = (index % numberList.size) == selectedIndex

                    CommonText(
                        modifier = if (!isSelectedIndex) Modifier.alpha(0.5f) else Modifier,
                        text = item.toString(),
                        textStyle = if (isSelectedIndex)
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