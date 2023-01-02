package com.visualprogrammingclass.boncal.views

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.visualprogrammingclass.boncal.viewModels.CalculateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun CalculateScreen(
    navController: NavController,
    calculateViewModel: CalculateViewModel = hiltViewModel()
){
    // navigate back to home
    // if they navigate out and back, it'll only take them to home
    // wasted memory...
}