package com.visualprogrammingclass.boncal.views

import android.graphics.Paint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.visualprogrammingclass.boncal.components.boncalRoundedShape
import com.visualprogrammingclass.boncal.viewModels.CalculateViewModel
import com.visualprogrammingclass.boncal.views.ui.theme.Blue400
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun CalculateScreen(
    navController: NavController,
    calculateViewModel: CalculateViewModel = hiltViewModel()
){
    // Store: Amount
    // Store: ID


    // navigate back to home
    // if they navigate out and back, it'll only take them to home
    // wasted memory...

    // bubble about

    // bubble infosss
    Box(modifier = Modifier
        .clip(boncalRoundedShape())
        .background(Blue400)
        .padding(4.dp)
        .fillMaxWidth()
    ){
        Box(
            contentAlignment = Alignment.Center
        ){

        }
    }

    // text input layout

    val (emission, setEmission) = rememberSaveable { mutableStateOf("") }
    TextField(
        modifier = Modifier
            .clip(boncalRoundedShape())
            .fillMaxWidth(),

        value = emission, onValueChange = {newText -> setEmission(newText)}

    )

    // button calculate
    Button(onClick = {
//        calculateViewModel.sendingCarbonEmissionData()
    }) {

    }

}