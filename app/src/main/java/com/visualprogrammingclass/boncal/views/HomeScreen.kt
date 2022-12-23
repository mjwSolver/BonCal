package com.visualprogrammingclass.boncal.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.visualprogrammingclass.boncal.helpers.statics
import com.visualprogrammingclass.boncal.services.navigations.Screen
import com.visualprogrammingclass.boncal.views.ui.theme.Slate50
import com.visualprogrammingclass.boncal.views.ui.theme.Slate900

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text("Hello Android")

        Text(text = "Welcome to the HomeScreen",
            color = UseColor(dark = Slate50, light = Slate900))

    }
}



//        Button(onClick = {
//            statics.logged = false
//        }){
//            Text(text = "Log Out - Don't press")
//        }
//        Button(onClick = { navController.navigate(Screen.Register.route)}) {
//            Text("Register route")
//        }