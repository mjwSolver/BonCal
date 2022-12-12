package com.visualprogrammingclass.boncal.views

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Icon
import android.widget.NumberPicker.OnValueChangeListener
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldColorsWithIcons
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
import androidx.core.content.ContextCompat.startActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.viewmodel.compose.viewModel
import com.visualprogrammingclass.boncal.models.LoginDetail
import com.visualprogrammingclass.boncal.ui.theme.Slate50
import com.visualprogrammingclass.boncal.ui.theme.Slate900
import com.visualprogrammingclass.boncal.viewModels.RegisterViewModel
import kotlinx.coroutines.flow.first
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.visualprogrammingclass.boncal.helper.statics
import java.util.prefs.Preferences

@Composable
fun  RegisterScreen(theContext: Context) {
    Column(modifier = Modifier.padding(16.dp)) {
//        dataStore = DataStore(name = "Setting")

        // At the top level of your kotlin file:
//        val dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

        if(statics.logged){
            Intent(theContext, RegisterActivity::class.java).let {
                theContext.startActivity(it)
            }
        }

        val registViewModel: RegisterViewModel = viewModel()
//            ViewModelProvider()[RegisterViewModel::class.java]

        val theTokenName: State<String> = registViewModel.tokenName.observeAsState("")
        val theToken: State<String> = registViewModel.token.observeAsState("")
        val theName: State<String> = registViewModel.name.observeAsState("")

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

