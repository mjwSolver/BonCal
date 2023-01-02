package com.visualprogrammingclass.boncal.views

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.visualprogrammingclass.boncal.services.navigations.BoncalNavbar
import com.visualprogrammingclass.boncal.services.navigations.main.NavbarScreen
import com.visualprogrammingclass.boncal.services.navigations.main.SetupNavBarGraph

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun MainScreen(navController: NavController){

    val navBarController = rememberNavController()

    SetupNavBarGraph(
        navBarController,
        NavbarScreen.HomeNB.route
    )


    val navBackStackEntry by navBarController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        bottomBar = {
            if (currentDestination != null) {
                BoncalNavbar(
                    currentScreenRoute = currentDestination,
                    onItemSelected = { navBarController.navigate(it.route) }
                )
            }
        }
    ) {

    }
}






