package com.visualprogrammingclass.boncal.views

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.visualprogrammingclass.boncal.viewModels.RegisterViewModel

@Composable
fun  RegisterScreen(
    navController: NavController,
    theContext: Context = LocalContext.current,
    registerViewModel: RegisterViewModel = hiltViewModel()) {

    val registViewModel: RegisterViewModel = viewModel()
//    val theTokenName: State<String> = registViewModel.tokenName.observeAsState("")
//    val theToken: State<String> = registViewModel.token.observeAsState("")
//    val theName: State<String> = registViewModel.name.observeAsState("")

    Column(modifier = Modifier.padding(16.dp)) {

    }
}

@Composable
@Preview
fun PreviewRegisterScreen() {

        val navController = rememberNavController()
        RegisterScreen(navController, LocalContext.current)
}

