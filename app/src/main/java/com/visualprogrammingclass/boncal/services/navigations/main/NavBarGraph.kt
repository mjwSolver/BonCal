package com.visualprogrammingclass.boncal.services.navigations.main

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.visualprogrammingclass.boncal.services.navigations.Screen
import com.visualprogrammingclass.boncal.views.*
import java.net.URLDecoder

@OptIn(ExperimentalFoundationApi::class)
@ExperimentalAnimationApi
@Composable
fun SetupNavBarGraph(navController: NavHostController, startDestination: String) {
    NavHost(
        navController = navController, startDestination = startDestination
    ) {
        composable(route = Screen.Empty.route) {
            EmptyScreen()
        }
        composable(route = NavbarScreen.HomeNB.route) {
//            HomeScreen(navController = navController)
            HomeScreen(navController = navController)
        }
        composable(route = NavbarScreenChildren.Category.route,) { CategoryScreen(navController) }
        composable(
            route = NavbarScreenChildren.Calculate.route,
            arguments = listOf(
                navArgument(CALCULATE_REQUIRED_CATEGORY_ID) { type = NavType.IntType },
                navArgument(CATEGORY_AS_STRING) { type = NavType.StringType }
            )
        ) {
            val calculateId = it.arguments?.getString(CALCULATE_REQUIRED_CATEGORY_ID).toString()
            val categoryAsString = it.arguments?.getString(CATEGORY_AS_STRING).toString()
            val decodedCategory = URLDecoder.decode(categoryAsString, "UTF-8")

            CalculateScreen(navController, calculateId, decodedCategory)
        }
        composable(
            route = NavbarScreenChildren.WidgetWebView.route,
            arguments = listOf(
                navArgument("url") { type = NavType.StringType },
            )
        ) {
            val calculateId = it.arguments?.getString(CALCULATE_REQUIRED_CATEGORY_ID).toString()
            CalculateScreen(navController, calculateId)
        }

        composable(route = NavbarScreen.LeaderboardNB.route) {
//            LeaderboardScreen(navController = navController)
            LeaderboardScreen()
        }
        composable(route = NavbarScreen.ProfileNB.route) {
            ProfileScreen()
        }

    }
}
