package com.visualprogrammingclass.boncal.views

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun UseColor(dark: Color, light: Color): Color = if(isSystemInDarkTheme()) dark else light