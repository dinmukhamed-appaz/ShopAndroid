package com.example.shopapp.ui.theme

import androidx.compose.material3.Typography

val Typography = with(Typography()) {
    copy(
        bodyLarge = bodyLarge.copy(fontFamily = Geist),
        bodyMedium = bodyMedium.copy(fontFamily = Geist),
        bodySmall = bodySmall.copy(fontFamily = Geist),
        titleLarge = titleLarge.copy(fontFamily = Geist),
        labelLarge = labelLarge.copy(fontFamily = Geist)
    )
}
