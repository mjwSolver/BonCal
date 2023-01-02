package com.visualprogrammingclass.boncal.services.navigations

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.visualprogrammingclass.boncal.services.navigations.main.NavbarScreen
import com.visualprogrammingclass.boncal.views.ui.theme.*

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BoncalNavbar(
    currentScreenRoute: NavDestination,
    onItemSelected:(NavbarScreen)->Unit
) {

    val navBarItems = NavbarScreen.Items.list

    Row(
        modifier= Modifier
            .background(backgroundColor())
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {


        navBarItems.forEach { item ->
            BoncalBottomNavigationItem(
                item = item,
//                isSelected = item.route == currentScreenRoute
                isSelected = currentScreenRoute.hierarchy.any{ it.route == item.route },

            ) {
                onItemSelected(item) // onClick Response
            }
        }

    }

}

@ExperimentalAnimationApi
@Composable
fun BoncalBottomNavigationItem(item: NavbarScreen, isSelected:Boolean, onClick:()->Unit){

    val background =
        if(isSystemInDarkTheme())
            if (isSelected) Blue400.copy(alpha = 0.1f) else Color.Transparent
        else
            if (isSelected) Blue600.copy(alpha = 0.1f) else Color.Transparent
    val contentColor =
        if(isSystemInDarkTheme())
            if (isSelected) Sky500 else Emerald400
        else
            if (isSelected) Sky500 else Teal500

    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(background)
            .clickable(onClick = onClick)
    ){
        Row(
            modifier=Modifier
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {

            Icon(
                painter = painterResource(id = item.icon),
                contentDescription = null,
                tint = contentColor
            )

            AnimatedVisibility(visible = isSelected) {
                Text(
                    text = item.title,
                    color = contentColor
                )
            }

        }
    }


}


@Composable
@Preview
fun Prev1(){

//    val navController = rememberNavController()
//    val navBackStackEntry by navController.currentBackStackEntryAsState()
//    val currentDestination = navBackStackEntry?.destination

//    if (currentDestination != null) {
        BoncalNavbarToPreview(
            currentScreenRoute = NavbarScreen.LeaderboardNB.route) {
        }
//    }
}

@Composable
@Preview(uiMode = UI_MODE_NIGHT_YES)
fun Prev1Dark(){

//    val navController = rememberNavController()
//    val navBackStackEntry by navController.currentBackStackEntryAsState()
//    val currentDestination = navBackStackEntry?.destination

//    if (currentDestination != null) {
    BoncalNavbarToPreview(
        currentScreenRoute = NavbarScreen.LeaderboardNB.route) {
    }
//    }
}

@ExperimentalAnimationApi
@Composable
@Preview
fun Prev2() {
    BoncalBottomNavigationItem(item = NavbarScreen.HomeNB, isSelected = true) {

    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BoncalNavbarToPreview(
    currentScreenRoute: String,
    onItemSelected:(NavbarScreen)->Unit
) {

    val navBarItems = NavbarScreen.Items.list

    Row(
        modifier= Modifier
            .background(backgroundColor())
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {


        navBarItems.forEach { item ->
            BoncalBottomNavigationItem(
                item = item,
                isSelected = item.route == currentScreenRoute
//                isSelected = currentScreenRoute.hierarchy.any{ it.route == item.route },
                ) {
                onItemSelected(item) // onClick Response
            }
        }

    }

}