package com.visualprogrammingclass.boncal.services.navigations

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.visualprogrammingclass.boncal.Application
import com.visualprogrammingclass.boncal.helpers.statics
import com.visualprogrammingclass.boncal.views.AnimatedSplashScreen
import com.visualprogrammingclass.boncal.views.HomeScreen
import com.visualprogrammingclass.boncal.views.MainActivity

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
//        startDestination = Screen.Home.route
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) {
            AnimatedSplashScreen(navController = navController)
        }
        composable(route = Screen.Register.route) {
            startActivity(Application(), Intent(Application(), MainActivity::class.java), null)
        }
        composable(route = Screen.Home.route){
            HomeScreen(navController = navController)
        }
    }
}