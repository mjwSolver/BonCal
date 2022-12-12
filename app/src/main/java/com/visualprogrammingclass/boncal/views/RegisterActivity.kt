package com.visualprogrammingclass.boncal.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.visualprogrammingclass.boncal.navigations.SetupNavGraph
import com.visualprogrammingclass.boncal.ui.theme.Slate50
import com.visualprogrammingclass.boncal.ui.theme.Slate900
import com.visualprogrammingclass.boncal.views.ui.theme.BonCalTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
                    SetupNavGraph(navController = NavHostController(this))
                    
                    RegisterScreen(theContext = this)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    BonCalTheme {
        RegisterScreen(theContext = LocalContext.current)
    }
}