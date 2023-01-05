package com.visualprogrammingclass.boncal.views

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.visualprogrammingclass.boncal.components.BoncalGradientButton
import com.visualprogrammingclass.boncal.components.BoncalPasswordTextInputLayout
import com.visualprogrammingclass.boncal.components.BoncalTextInputLayout
import com.visualprogrammingclass.boncal.models.authentication.RegisterDetail
import com.visualprogrammingclass.boncal.services.navigations.Screen
import com.visualprogrammingclass.boncal.viewModels.RegisterViewModel
import com.visualprogrammingclass.boncal.views.ui.theme.logo

@Composable
fun  RegisterScreen(
    navController: NavController,
    context: Context = LocalContext.current,
    theFocusManager: FocusManager = LocalFocusManager.current,
    registerViewModel: RegisterViewModel = hiltViewModel()) {

    val theSuccess: State<Boolean> = registerViewModel.success.observeAsState(false)
//    val scrollState = rememberScrollState()

    Box(
//        modifier = Modifier
//            .scrollable(state = scrollState, orientation = Orientation.Vertical),
//            .verticalScroll(state = scrollState),
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier.fillMaxSize().padding(8.dp),
//                .verticalScroll(state = scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
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

            val (name, setName) = rememberSaveable { mutableStateOf("") }
            BoncalTextInputLayout(
                label = "Name",
                leadIcon = Icons.Default.AccountCircle,
                text = name, setText = setName,
                onClick = {setName("")},
                focusManager = theFocusManager)

            val (email, setEmail) = rememberSaveable { mutableStateOf("") }
            BoncalTextInputLayout(
                label = "Email",
                text = email, setText = setEmail,
                onClick = {setEmail("")},
                focusManager = theFocusManager)

            val (password, setPassword) = rememberSaveable{ mutableStateOf("") }
            BoncalPasswordTextInputLayout(
                password = password,
                setPassword = setPassword,
                focusManager = theFocusManager
            )

            val (repeat, setRepeat) = rememberSaveable{ mutableStateOf("") }
            BoncalPasswordTextInputLayout(
                label = "repeat password",
                password = repeat,
                setPassword = setRepeat,
                focusManager = theFocusManager
            )

            ClickableText(
                text = AnnotatedString("Login with an Account"),
                onClick = {
                    navController.popBackStack()
                    navController.navigate(Screen.Login.route)
                }
            )

            BoncalGradientButton(
    //                    modifier = Modifier
    //                        .fillMaxWidth(),
                text = "Register",
                onClick = {

                    val validationResult = validateEmailAndPassword(email = email, password = password)
                    if(validationResult.isNotEmpty()){
                        Toast.makeText(context, "Please fill in $validationResult Field", Toast.LENGTH_SHORT).show()
                        return@BoncalGradientButton
                    }

                    registerViewModel.registerThisUser(
                        context,
                        RegisterDetail(name,email,password,repeat)
                    )

                    Log.d("regist_button", "button pressed")
                    Log.d("regist_email", email)
                    Log.d("regist_password", password)

                    // Need authentication...
                    val authenticationSucceeded = theSuccess.value
                    if(authenticationSucceeded){
                        navController.popBackStack()
                        navController.navigate(Screen.Main.route)
                    } else {
                        Toast.makeText(
                            context, "Authentication Failed",
                            Toast.LENGTH_SHORT).show()
                    }
                }
            )

    }

    }
}

@Composable
@Preview
fun PreviewRegisterScreen() {

        val navController = rememberNavController()
        RegisterScreen(navController)
}

