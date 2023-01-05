package com.visualprogrammingclass.boncal.views

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.visualprogrammingclass.boncal.viewModels.CategoryViewModel

@Composable
fun CategoryScreen(
    navController: NavController,
    categoryViewModel: CategoryViewModel = hiltViewModel()
){
    // navigate - don't pop backstack




}
