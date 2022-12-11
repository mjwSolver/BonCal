package com.visualprogrammingclass.boncal.views

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.unit.dp
import com.visualprogrammingclass.boncal.ui.theme.Purple80

@Composable
fun AnimatedSplashScreen(){
    Splash()
}

@Composable
fun Splash() {
    Box(
        modifier = Modifier
            .background(if (isSystemInDarkTheme()) Color.Black else Purple80)
            .fillMaxSize()
    ){
        Icon(
            modifier = Modifier.size(120.dp),
            imageVector = Icons.Default.Email,
            contentDescription = "Logo Icon",
            tint = Color.White
        )
    }
}