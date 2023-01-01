package com.visualprogrammingclass.boncal.services.navigations

import com.visualprogrammingclass.boncal.R

sealed class NavbarScreen(
    val route:String,
    val title:String,
    val icon: Int,
){

    object HomeNB:NavbarScreen("home","Home", R.drawable.home_icon)
    object LeaderboardhNB:NavbarScreen("leaderboard","Leaderboard", R.drawable.leaderboard_icon)
    object ProfileNB:NavbarScreen("profile","Profile", R.drawable.profile_icon)

    object Items{
        val list= listOf(
            HomeNB,LeaderboardhNB,ProfileNB
        )
    }

}