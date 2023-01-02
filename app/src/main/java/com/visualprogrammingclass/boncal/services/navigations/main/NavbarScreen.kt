package com.visualprogrammingclass.boncal.services.navigations.main

import com.visualprogrammingclass.boncal.R
import com.visualprogrammingclass.boncal.services.navigations.Screen

sealed class NavbarScreen(
    val route:String,
    val title:String,
    val icon: Int,
){

    object HomeNB: NavbarScreen("home","Home", R.drawable.home_icon)
    object LeaderboardNB: NavbarScreen("leaderboard","Leaderboard", R.drawable.leaderboard_icon)
    object ProfileNB: NavbarScreen("profile","Profile", R.drawable.profile_icon)

    object Items{
        val list= listOf(
            HomeNB, LeaderboardNB, ProfileNB
        )
    }

}

sealed class NavbarScreenChildren(
    val route: String
){
    object Category: NavbarScreenChildren("category_screen")
    object Calculate: NavbarScreenChildren("calculate_screen")
}