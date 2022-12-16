package com.visualprogrammingclass.boncal.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import com.visualprogrammingclass.boncal.helpers.statics
import com.visualprogrammingclass.boncal.repositories.SplashViewModel
import com.visualprogrammingclass.boncal.services.navigations.SetupNavGraph
import com.visualprogrammingclass.boncal.views.theme.BonCalTheme
import com.visualprogrammingclass.boncal.views.theme.Slate50
import com.visualprogrammingclass.boncal.views.theme.Slate900
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        installSplashScreen().setKeepOnScreenCondition {
            !splashViewModel.isLoading.value
        }

        setContent {
            BonCalTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = UseColor(Slate900, Slate50)
                ) {
                    Text(text = "Welcome to the HomeScreen")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(
        text = "Hello $name!",
        style = MaterialTheme.typography.titleSmall,
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BonCalTheme {
        Greeting("Android")
    }
}