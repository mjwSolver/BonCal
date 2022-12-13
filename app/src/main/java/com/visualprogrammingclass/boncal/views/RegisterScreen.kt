package com.visualprogrammingclass.boncal.views

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.visualprogrammingclass.boncal.helpers.statics
import com.visualprogrammingclass.boncal.models.LoginDetail
import com.visualprogrammingclass.boncal.ui.theme.Slate50
import com.visualprogrammingclass.boncal.ui.theme.Slate900
import com.visualprogrammingclass.boncal.viewModels.RegisterViewModel

@Composable
fun  RegisterScreen(theContext: Context) {

    val registViewModel: RegisterViewModel = viewModel()
    val theTokenName: State<String> = registViewModel.tokenName.observeAsState("")
    val theToken: State<String> = registViewModel.token.observeAsState("")
    val theName: State<String> = registViewModel.name.observeAsState("")

    Column(modifier = Modifier.padding(16.dp)) {

        if(statics.logged){
            Intent(theContext, RegisterActivity::class.java).let {
                theContext.startActivity(it)
            }
        }

        statics.token = theToken.value

        Text(
            text = "Generated token Name ${theTokenName.value}",
            color = UseColor(dark = Slate50, light = Slate900)
        )
        Text(
            text = "Generated token Value ${theToken.value}",
            color = UseColor(dark = Slate50, light = Slate900)
        )
        Text(
            text = "The user ${theName.value}",
            color = UseColor(dark = Slate50, light = Slate900)
        )

        Button(onClick = {
            theContext.openFileOutput("login.txt", Context.MODE_PRIVATE).use {
                it.write("Hello world login".toByteArray())
                it.close()
            }
        }) {
            Text(text = "Write login.txt")
        }

        Button(onClick = {
            var text: String = ""
            theContext.openFileInput("login.txt").bufferedReader().useLines { lines ->
                text = lines.fold("") { some, text ->
                    "$some\n$text"
                }
            }
            Toast.makeText(theContext, text, Toast.LENGTH_SHORT).show()
        }) {
            Text(text = "Read login.txt")
        }

        Button(onClick = {
            val loginDetails = LoginDetail("rama@rama-adi.dev", "password", true)
            registViewModel.loginThisUser(theContext, loginDetails)
        }) {
            Text(text = "Login!!!")
        }

        val (email, setEmail) = remember { mutableStateOf("") }
        val (password, setPassword) = remember { mutableStateOf("") }

        CustomTextInputLayout(
            label = "Email",
            onValueChanged = setEmail
        )
        CustomTextInputLayout(
            label = "Password",
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChanged = setPassword
        )
        
//        Checkbox(checked = true, onCheckedChange = )

        Button(onClick = {
            val loginDetails = LoginDetail(email, password, false)
            statics.logged = true
            registViewModel.loginThisUser(theContext, loginDetails).invokeOnCompletion {
                Intent(theContext, RegisterActivity::class.java).let {
                    theContext.startActivity(it)
                }
            }

        }) {Text(text = "Login!!!")}
    }
}

//private lateinit var dataStore: DataStore<Preferences>

//private suspend fun save(context: Context, key:String, value: String){
//    val dataStoreKey = stringPreferencesKey(key)
//    dataStore. { settings -> settings[dataStoreKey]= value}
//}

//private suspend fun read(key:String): String? {
//    val dataStoreKey = stringPreferencesKey(key)
//    val preferences = dataStore.data.first()
//    return preferences[dataStoreKey]
//}

@Composable
fun CustomTextInputLayout(
    label:String,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    leadIcon: ImageVector = Icons.Default.Email,
//    trailIcon: ImageVector = Icons.
    onValueChanged: (String) -> Unit
){
    val (text, setText) = remember { mutableStateOf("") }
    OutlinedTextField(
        value = text, onValueChange = setText,
        leadingIcon = { Icon(imageVector = leadIcon, contentDescription = "")
//        trailingIcon = { Icon(imageVector = )}
        }
    )
}

@Composable
@Preview
fun PreviewRegisterScreen() {
    RegisterScreen(LocalContext.current)
}

