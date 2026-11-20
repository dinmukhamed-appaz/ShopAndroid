package com.example.shopapp.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val Ink = Color(0xFF111111)
val Graphite = Color(0xFF1C1C1E)
val Mist = Color(0xFFE8EAEB)
val Smoke = Color(0xFF6B6B6F)
val Paper = Color(0xFFFFFFFF)

@Immutable
data class ExtraColors(
    val bannerStart: Color = Color(0xFF111111),
    val bannerEnd: Color = Color(0xFF4A4A4A),
    val imageTop: Color = Color(0xFFE4E4E6),
    val imageBottom: Color = Color(0xFFC9C9CC),
    val iconButtonBackground: Color = Mist
)