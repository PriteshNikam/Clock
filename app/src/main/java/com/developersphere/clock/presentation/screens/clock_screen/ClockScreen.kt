package com.developersphere.clock.presentation.screens.clock_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.developersphere.clock.presentation.common_compose.CommonText
import com.developersphere.clock.ui.theme.BackGroundColor
import com.developersphere.clock.ui.theme.BottomAppBarBackgroundColor
import com.developersphere.clock.ui.theme.Green
import com.developersphere.clock.ui.theme.LightGrey
import com.developersphere.clock.ui.theme.White
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

data class WorldClockModel(val city: String, val timeZone: String)

@RequiresApi(Build.VERSION_CODES.O)
val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ClockScreen(clockScreenViewModel: ClockScreenViewModel = hiltViewModel()) {

    val currentTime = clockScreenViewModel.currentTime.collectAsState()

    val worldClockList = listOf(
        WorldClockModel("New Delhi", "Asia/Kolkata"),
        WorldClockModel("New York", "America/New_York"),
        WorldClockModel("London", "Europe/London"),
        WorldClockModel("Tokyo", "Asia/Tokyo"),
        WorldClockModel("Sydney", "Australia/Sydney"),
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(BackGroundColor)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        item {
            Column(modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ){
                CommonText(
                    text = currentTime.value,
                    textStyle = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily.Monospace,
                        color = White,
                        letterSpacing = 2.sp
                    ),
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                Text(
                    "Indian Standard Time",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily.Monospace,
                    color = LightGrey,
                )
            }
            Spacer(modifier = Modifier.height(44.dp))
        }


        items(worldClockList) { clock ->
            val time = remember { mutableStateOf("") }

            LaunchedEffect(Unit) {
                while (isActive) {
                    val now = ZonedDateTime.now(ZoneId.of(clock.timeZone))
                    time.value = now.format(formatter)
                    delay(1000) // Update every second
                }
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(containerColor = BottomAppBarBackgroundColor)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = clock.city,
                        fontSize = 20.sp,
                        color = White,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = time.value,
                        fontSize = 18.sp,
                        color = Green
                    )
                }
            }
        }
    }
}