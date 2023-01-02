package com.visualprogrammingclass.boncal.services.navigations

sealed class Screen(val route:String) {
//    object Splash: Screen("splash_screen")
    object Empty: Screen("empty_screen")
    object Register: Screen("register_screen")
    object Welcome: Screen("welcome_screen")
    object Login: Screen("login_screen")

    object Main: Screen("main_screen")
    object Home: Screen("home_screen")
    // in NavBarScreen
        object Category: Screen("category_screen")
        object Calculate: Screen("calculate_screen")
//    object Leaderboard: Screen("leaderboard_screen")
//    object Profile: Screen("profile_screen")
}