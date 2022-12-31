package com.visualprogrammingclass.boncal.services.navigations

sealed class Screen(val route:String) {
    object Splash: Screen("splash_screen")
    object Home: Screen("home_screen")
    object Register: Screen("register_screen")
    object Welcome: Screen("welcome_screen")
    object Login: Screen("login_screen")
}