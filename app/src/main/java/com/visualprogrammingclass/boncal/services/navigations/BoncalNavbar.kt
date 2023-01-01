package com.visualprogrammingclass.boncal.services.navigations

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.visualprogrammingclass.boncal.views.ui.theme.*

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BoncalNavbar(
    currentScreenRoute:String,
    onItemSelected:(NavbarScreen)->Unit
) {

    val navBarItems = NavbarScreen.Items.list

    Row(
        modifier= Modifier
            .background(Slate900)
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {

        navBarItems.forEach { item ->

            BoncalBottomNavigationItem(item = item, isSelected = item.route == currentScreenRoute) {
                onItemSelected(item) // onClick Response
            }

        }

    }

}

@ExperimentalAnimationApi
@Composable
fun BoncalBottomNavigationItem(item:NavbarScreen, isSelected:Boolean, onClick:()->Unit){

    val background = if (isSelected) Blue400.copy(alpha = 0.1f) else Color.Transparent
    val contentColor = if (isSelected) Sky500 else Emerald400

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
    BoncalNavbar(currentScreenRoute = NavbarScreen.HomeNB.route) {

    }
}

@ExperimentalAnimationApi
@Composable
@Preview
fun Prev2() {
    BoncalBottomNavigationItem(item = NavbarScreen.HomeNB, isSelected = true) {

    }
}