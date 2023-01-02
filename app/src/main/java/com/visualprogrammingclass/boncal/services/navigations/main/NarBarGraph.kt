package com.visualprogrammingclass.boncal.services.navigations.main

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.visualprogrammingclass.boncal.services.navigations.Screen
import com.visualprogrammingclass.boncal.views.EmptyScreen
import com.visualprogrammingclass.boncal.views.HomeScreen
import com.visualprogrammingclass.boncal.views.LeaderboardScreen
import com.visualprogrammingclass.boncal.views.ProfileScreen

@ExperimentalAnimationApi
@Composable
fun SetupNavBarGraph(navController: NavHostController, startDestination: String) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Screen.Empty.route) {
            EmptyScreen()
        }
        composable(route = NavbarScreen.HomeNB.route){
//            HomeScreen(navController = navController)
            HomeScreen()
        }
        composable(route = NavbarScreen.LeaderboardNB.route){
//            LeaderboardScreen(navController = navController)
            LeaderboardScreen()
        }
        composable(route = NavbarScreen.ProfileNB.route){
            ProfileScreen()
        }

    }
}
