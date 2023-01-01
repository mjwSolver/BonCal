package com.visualprogrammingclass.boncal.views.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.material3.Text
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.visualprogrammingclass.boncal.R

val Inter = FontFamily(
    Font(R.font.relay_inter_thin, weight = FontWeight.W100),
    Font(R.font.relay_inter_extralight, weight = FontWeight.W200),
    Font(R.font.relay_inter_light, weight = FontWeight.W300),
    Font(R.font.relay_inter_regular, weight = FontWeight.W400),
    Font(R.font.relay_inter_medium, weight = FontWeight.W500),
    Font(R.font.relay_inter_semibold, weight = FontWeight.W600),
    Font(R.font.relay_inter_bold, weight = FontWeight.W700),
    Font(R.font.relay_inter_extrabold, weight = FontWeight.W800),
    Font(R.font.relay_inter_black, weight = FontWeight.W900),
)

// Option + Shift, saves you the repetition.
val InterTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = Inter
    ),
    displayMedium = TextStyle(
        fontFamily = Inter
    ),
    displaySmall = TextStyle(
        fontFamily = Inter
    ),
    headlineLarge = TextStyle(
        fontFamily = Inter
    ),
    headlineMedium = TextStyle(
        fontFamily = Inter
    ),
    headlineSmall = TextStyle(
        fontFamily = Inter
    ),
    titleLarge = TextStyle(
        fontFamily = Inter
    ),
    titleMedium = TextStyle(
        fontFamily = Inter
    ),
    titleSmall = TextStyle(
        fontFamily = Inter,
    ),
    bodyLarge = TextStyle(
        fontFamily = Inter,
    ),
    bodyMedium = TextStyle(
        fontFamily = Inter
    ),
    bodySmall = TextStyle(
        fontFamily = Inter,
        fontSize = 12.sp
    ),
    labelLarge = TextStyle(
        fontFamily = Inter
    ),
    labelMedium = TextStyle(
        fontFamily = Inter
    ),
    labelSmall = TextStyle(
        fontFamily = Inter
    )
)
