package com.developersphere.clock.presentation.common_compose

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun CommonText(text:String, modifier: Modifier? = Modifier, textStyle: TextStyle? = null) {
    Text(text = text, modifier = modifier ?: Modifier, style = textStyle ?: TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        color = Color.White,
    )
    )
}