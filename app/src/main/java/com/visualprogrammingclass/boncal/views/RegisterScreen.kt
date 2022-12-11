package com.visualprogrammingclass.boncal.views

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.visualprogrammingclass.boncal.models.LoginDetail
import com.visualprogrammingclass.boncal.ui.theme.Slate50
import com.visualprogrammingclass.boncal.ui.theme.Slate900
import com.visualprogrammingclass.boncal.viewModels.RegisterViewModel

@Composable
fun RegisterScreen(theContext: Context) {
    Column(modifier = Modifier.padding(16.dp)) {

        val registViewModel: RegisterViewModel = viewModel()
//            ViewModelProvider()[RegisterViewModel::class.java]

        val theName: State<String> = registViewModel.name.observeAsState("")
        val theToken: State<String> = registViewModel.token.observeAsState("")

        Text(text = "Generated token Name ${theName.value}", color = UseColor(dark = Slate50, light = Slate900))
        Text(text = "Generated token Value ${theToken.value}", color = UseColor(dark = Slate50, light = Slate900))

        Button(onClick = {
            val loginDetails = LoginDetail("rama@rama-adi.dev", "password", true)
            registViewModel.loginThisUser(theContext, loginDetails)
        }) {
            Text(text = "Login!!!")
        }


    }
}

@Composable
@Preview
fun PreviewRegisterScreen() {
    RegisterScreen(LocalContext.current)
}

