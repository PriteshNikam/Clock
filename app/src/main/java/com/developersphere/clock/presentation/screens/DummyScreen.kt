package com.developersphere.clock.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.developersphere.clock.presentation.common_compose.CommonText
import com.developersphere.clock.presentation.navigation.routes.AlarmRoute

@Composable
fun DummyScreen(navigation:(destination: AlarmRoute?)-> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CommonText(text = "Dummy BottomNavRoute", textStyle = TextStyle(fontSize = 24.sp))
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = {
            navigation(null)
        }) {
            CommonText(text = "go back")
        }
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = {
            navigation(AlarmRoute.AlarmScreen)
        }) {
            CommonText(text = "go root")
        }
    }
}