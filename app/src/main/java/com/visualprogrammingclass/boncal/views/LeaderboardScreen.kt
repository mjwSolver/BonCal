package com.visualprogrammingclass.boncal.views

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.visualprogrammingclass.boncal.viewModels.LeaderboardViewModel
import com.visualprogrammingclass.boncal.views.ui.theme.Slate900

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeaderboardScreen(
//    navController: NavController,
    leaderBoardViewModel: LeaderboardViewModel = hiltViewModel()
){

    val theToken: State<String?> = leaderBoardViewModel.token.observeAsState()
    leaderBoardViewModel.getUserToken()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Leaderboard",
            style = MaterialTheme.typography.titleLarge
        )
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("BonCal", color = Color.White) },
            backgroundColor = Slate900
        ) },
        content = {
            LeaderboardContent(theToken.value.toString())
        }
    )

}


@Composable
fun LeaderboardContent(token:String){

        // Declare a string that contains a url
        val mUrl = "https://boncal.rama-adi.dev/temp-webview/leaderboard?token=$token"

        // Adding a WebView inside AndroidView
        // with layout as full screen
        AndroidView(factory = {
            WebView(it).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                webViewClient = WebViewClient()
                loadUrl(mUrl)
            }
        }, update = {
            it.loadUrl(mUrl)
        })
    }
