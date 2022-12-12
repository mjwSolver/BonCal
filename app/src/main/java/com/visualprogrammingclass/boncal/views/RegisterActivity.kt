package com.visualprogrammingclass.boncal.views

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.visualprogrammingclass.boncal.helper.statics
import com.visualprogrammingclass.boncal.ui.theme.Slate50
import com.visualprogrammingclass.boncal.ui.theme.Slate900
import com.visualprogrammingclass.boncal.views.ui.theme.BonCalTheme

//@AndroidEntryPoint
class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BonCalTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = UseColor(dark = Slate900, light = Slate50)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text("Hello Android")
                        Button(onClick = {
                            setLogOut()
                        }){
                            Text(text = "Log Out")
                        }
                    }
                }
            }
        }
    }
}

fun setLogOut(){
    statics.logged = false
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    BonCalTheme {
        RegisterScreen(theContext = LocalContext.current)
    }
}