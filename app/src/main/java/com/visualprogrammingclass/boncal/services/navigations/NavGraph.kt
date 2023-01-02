package com.visualprogrammingclass.boncal.services.navigations

import android.content.Intent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.visualprogrammingclass.boncal.Application
import com.visualprogrammingclass.boncal.views.*
import com.visualprogrammingclass.boncal.views.onboarding.WelcomeScreen

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun SetupNavGraph(navController: NavHostController, startDestination: String) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Screen.Empty.route){
            EmptyScreen()
        }
        composable(route = Screen.Welcome.route){
            WelcomeScreen(navController = navController)
        }

        composable(route = Screen.Login.route){
            LoginScreen(navController = navController)
        }

        composable(route = Screen.Register.route){
            RegisterScreen(navController = navController)
        }

        composable(route = Screen.Main.route){
            MainScreen(navController = navController)
        }

//        composable(route = Screen.Home.route){
//            HomeScreen(navController = navController)
//        }


//        object Category: Screen("category_screen")
//        object Calculate: Screen("calculate_screen")
//        object Leaderboard: Screen("leaderboard_screen")
//        object Profile: Screen("profile_screen")

    }
}