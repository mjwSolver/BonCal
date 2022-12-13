package com.visualprogrammingclass.boncal.services.navigations

sealed class Screen(val route:String) {
    object Splash: Screen("splash_screen")
    object Home: Screen("home_screen")
    object Register: Screen("register_screen")
}