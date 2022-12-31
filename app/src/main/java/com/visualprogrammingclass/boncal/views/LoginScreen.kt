package com.visualprogrammingclass.boncal.views

import android.content.Context
import android.content.res.Configuration
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.visualprogrammingclass.boncal.components.BoncalCheckBox
import com.visualprogrammingclass.boncal.components.BoncalGradientButton
import com.visualprogrammingclass.boncal.components.BoncalPasswordTextInputLayout
import com.visualprogrammingclass.boncal.components.BoncalTextInputLayout
import com.visualprogrammingclass.boncal.models.authentication.LoginDetail
import com.visualprogrammingclass.boncal.services.navigations.Screen
import com.visualprogrammingclass.boncal.viewModels.LoginViewModel
import com.visualprogrammingclass.boncal.views.ui.theme.BonCalTheme
import com.visualprogrammingclass.boncal.views.ui.theme.logo

@Composable
fun LoginScreen(
    navController: NavController,
    context: Context = LocalContext.current,
    theFocusManager: FocusManager = LocalFocusManager.current,
    loginViewModel: LoginViewModel = hiltViewModel()
){

    val theSuccess: State<Boolean> = loginViewModel.success.observeAsState(false)
//    val theTokenName: State<String> = loginViewModel.tokenName.observeAsState("")
//    val theToken: State<String> = loginViewModel.token.observeAsState("")
//    val theName: State<String> = loginViewModel.name.observeAsState("")

    Box(
        modifier =
        Modifier
            .fillMaxSize()
            .horizontalScroll(rememberScrollState())
            .padding(16.dp),
        contentAlignment = Alignment.Center,

        ){
        // Hold the Alignments here
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id =
                    if(isSystemInDarkTheme()) MaterialTheme.logo.boncalBlack
                    else MaterialTheme.logo.boncalWhite
                    ),
                contentDescription = "Logo",
                modifier = Modifier
                    .width(128.dp),
                contentScale = ContentScale.FillWidth
            )

//            var email by rememberSaveable { mutableStateOf("") }
            val (email, setEmail) = rememberSaveable { mutableStateOf("") }
            BoncalTextInputLayout(
                label = "Email",
//                text = email, setText = { newEmail -> email = newEmail},
                text = email, setText = setEmail,
//                text = email,
                onClick = {setEmail("")},
                focusManager = theFocusManager)

            val (password, setPassword) = rememberSaveable{ mutableStateOf("") }
            BoncalPasswordTextInputLayout(
                password = password,
                setPassword = setPassword,
                focusManager = theFocusManager
            )

            // Not yet configured
            val (checked, setChecked) = rememberSaveable { mutableStateOf(false) }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                BoncalCheckBox(
                    checked, setChecked
                )
                Text(text = "Remember Me")
            }
                BoncalGradientButton(
//                    modifier = Modifier
//                        .fillMaxWidth(),
                    text = "Login",
                    onClick = {
                        loginViewModel.loginThisUser(
                            context,
                            LoginDetail(
                                email = email,
                                password = password,
                                remember =  checked
                            )
                        )
                        Log.d("login_button", "button pressed")
                        Log.d("login_email", email)
                        Log.d("login_password", password)

                        // Need authentication...
//                        val authenticationSucceeded = theSuccess.value
//                        if(authenticationSucceeded){
                            navController.popBackStack()
                            navController.navigate(Screen.Home.route)
//                        } else {
//                            Toast.makeText(
//                                context,
//                                "Something wrong happened",
//                                Toast.LENGTH_SHORT).show()
//                        }
                    })

        }

    }
}

@Composable
@Preview
fun PreviewLightLoginScreen(){

    BonCalTheme() {
        Surface() {
//            LoginScreen(context = LocalContext.current)
        }
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
fun PreviewDarkLoginScreen(){

    BonCalTheme() {
        Surface(

        ) {
//            LoginScreen(context = LocalContext.current)
        }
    }
}