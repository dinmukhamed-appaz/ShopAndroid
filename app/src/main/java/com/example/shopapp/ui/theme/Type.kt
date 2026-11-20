package com.example.shopapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Typography = with(Typography()) {
    copy(
        titleLarge = titleLarge.copy(
            fontFamily = Geist,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        ),
        titleMedium = titleMedium.copy(fontFamily = Geist),
        bodyLarge = bodyLarge.copy(fontFamily = Geist),
        bodyMedium = bodyMedium.copy(fontFamily = Geist),
        bodySmall = bodySmall.copy(
            fontFamily = Geist,
            fontSize = 12.sp
        ),
        labelLarge = labelLarge.copy(fontFamily = Geist),
        labelMedium = labelMedium.copy(
            fontFamily = Geist,
            fontWeight = FontWeight.Light,
            fontSize = 12.sp,
            letterSpacing = 0.3.sp
        ),
        labelSmall = labelSmall.copy(fontFamily = Geist)
    )
}