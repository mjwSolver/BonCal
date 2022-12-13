package com.visualprogrammingclass.boncal.views

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.visualprogrammingclass.boncal.R
import com.visualprogrammingclass.boncal.R.drawable.boncallogolight
import com.visualprogrammingclass.boncal.R.drawable.boncallogodark
import com.visualprogrammingclass.boncal.services.navigations.Screen
import com.visualprogrammingclass.boncal.ui.theme.Slate50
import com.visualprogrammingclass.boncal.ui.theme.Slate900
import kotlinx.coroutines.delay

@Composable
fun AnimatedSplashScreen(navController: NavHostController) {
    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(durationMillis = 3000)
    )
    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(5000)
        navController.popBackStack()
        navController.navigate(Screen.Home.route)
    }
    Splash(alpha = alphaAnim.value)
}

@Composable
fun Splash(alpha: Float) {
    Box(
        modifier = Modifier
            .background(if (isSystemInDarkTheme()) Slate900 else Slate50)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
//        Icon(
//            modifier = Modifier.size(120.dp).alpha(alpha = alpha),
//            imageVector = Icons.Default.Email,
//            contentDescription = "Logo Icon",
//            tint = Color.White
//        )
        Image(
            painter = painterResource(
                id =
                if (isSystemInDarkTheme()) boncallogolight else boncallogodark
            ),
            contentDescription = "boncallogo.png",
            contentScale = ContentScale.FillHeight,
            alpha = alpha,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .size(175.dp)
        )
    }
}

@Composable
@Preview
fun SplashScreenPreview() {
    Splash(alpha = 1f)
}

@Composable
@Preview(uiMode = UI_MODE_NIGHT_YES)
fun SplashScreenDarkPreview() {
    Splash(alpha = 1f)
}
