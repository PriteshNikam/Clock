package com.developersphere.clock.presentation.screens.timer_screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.developersphere.clock.presentation.common_compose.CommonText
import com.developersphere.clock.presentation.common_compose.NormalDigitPicker
import com.developersphere.clock.ui.theme.BackGroundColor
import com.developersphere.clock.ui.theme.White

@Composable
fun TimerScreen() {
    val hourList = (0..99).toList()
    val minuteList = (0..59).toList()
    val secondsList = (0..59).toList()

    Column(
        Modifier
            .background(color = BackGroundColor)
            .fillMaxSize()
            .padding(
                vertical = 24.dp
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            //hour picker
            NormalDigitPicker(
                numberList = hourList,
                defaultSelectedDigit = 0,
                label = "Hours",
                updateSelectedDigit = {
                    Log.d("Clock", "Ra1 selectedValue :: $it")
                }
            )

            Box(modifier = Modifier.padding(top = 40.dp)){
                CommonText(
                    ":",
                    textStyle = TextStyle(
                        fontSize = 44.sp,
                        color = White,
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            // minute picker
            NormalDigitPicker(
                numberList = minuteList,
                defaultSelectedDigit = 15,
                label = "Minutes",
                updateSelectedDigit = {
                    Log.d("Clock", "Ra1 selectedValue :: $it")
                })

            Box(modifier = Modifier.padding(top = 40.dp)){
                CommonText(
                    ":",
                    textStyle = TextStyle(
                        fontSize = 44.sp,
                        color = White,
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            // seconds picker
            NormalDigitPicker(
                numberList = secondsList,
                defaultSelectedDigit = 0,
                label = "Seconds",
                updateSelectedDigit = {
                    Log.d("Clock", "Ra1 selectedValue :: $it")
                })
        }
    }
}