package com.developersphere.clock.presentation.screens.stop_watch_screen

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.developersphere.clock.presentation.common_compose.CommonText
import com.developersphere.clock.presentation.common_compose.CustomButton
import com.developersphere.clock.utils.StringFormatter
import androidx.compose.foundation.layout.*
import com.developersphere.clock.ui.theme.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StopWatchScreen(viewModel: StopWatchViewModel = hiltViewModel()) {

    val watch = viewModel.time.collectAsState()
    val isRunning = viewModel.isRunning.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackGroundColor)
            .padding(vertical = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CommonText(
            StringFormatter.formatTimeInMinSecMilliSec(watch.value),
            textStyle = TextStyle(
                letterSpacing = 12.sp,
                fontSize = 40.sp,
                color = if (isRunning) Green else White,
                fontWeight = FontWeight.SemiBold
            )
        )

        Spacer(modifier = Modifier.height(200.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // reset button
            CustomButton(
                action = {
                    viewModel.resetStopWatch()
                },
                buttonColor = BottomAppBarBackgroundColor,
                buttonTitle = "Reset"
            )

            // start/stop button
            CustomButton(
                action = {
                    if (isRunning)
                        viewModel.stopStopWatch()
                    else
                        viewModel.startStopWatch()
                },
                buttonColor = if (isRunning) Red else Green,
                buttonTitle = if (isRunning) "Stop" else "Start"
            )
        }
    }
}
