package com.visualprogrammingclass.boncal.views.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import com.visualprogrammingclass.boncal.R

data class Logo(
    // with BonCal Title
    val boncalBlack: Int = R.drawable.boncallogoblack,
    val boncalWhite: Int = R.drawable.boncallogowhite,
    // without Boncal Title
    val boncalDark: Int = R.drawable.boncallogodark,
    val boncalLight: Int = R.drawable.boncallogolight,
)

val LocalLogo = compositionLocalOf{ Logo() }

val MaterialTheme.logo: Logo
    @Composable
    @ReadOnlyComposable
    get() = LocalLogo.current