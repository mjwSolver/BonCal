package com.visualprogrammingclass.boncal.views.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val green = Color(0xFF22C55E) // Green400
val DarkTeal = Color(0xFF115E59) // Teal800

// Logo / Primary Colors
val Blue400 = Color(0xFF60A5FA)
val Emerald400 = Color(0xFF34D399)
 
// Secondary Color
val Sky500 = Color(0xFF0EA5E9) // Sky500
val Teal500 = Color(0xFF14B8A6) // Teal500

// Basic Light and Dark
val Slate50 = Color(0xFFF8FAFC)
val Slate900 = Color(0xFF0F172A)

val BoncalGradient = Brush.horizontalGradient( listOf(Blue400, Emerald400))

@Composable
fun backgroundColor() = if(isSystemInDarkTheme()) Slate900 else Slate50

@Composable
fun foregroundColor() = if(isSystemInDarkTheme()) Slate50 else Slate900