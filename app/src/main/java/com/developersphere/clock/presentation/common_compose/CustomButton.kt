package com.developersphere.clock.presentation.common_compose

import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.developersphere.clock.ui.theme.White

@Composable
fun CustomButton(
    action: (() -> Unit),
    buttonColor: Color? = null,
    buttonTitle: String? = null,
) {
    ElevatedButton(
        modifier = Modifier.width(120.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor ?: White
        ),
        onClick = {
            action()

        }) {
        CommonText(
            buttonTitle ?: "click",
            textStyle =
                TextStyle(
                    fontSize = 24.sp,
                    color = White,
                    fontWeight = FontWeight.SemiBold,
                ),
        )
    }
}