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
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.visualprogrammingclass.boncal.helper.statics
import com.visualprogrammingclass.boncal.navigations.SetupNavGraph
import com.visualprogrammingclass.boncal.ui.theme.BonCalTheme
import com.visualprogrammingclass.boncal.ui.theme.Slate50
import com.visualprogrammingclass.boncal.ui.theme.Slate900
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BonCalTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = UseColor(Slate900, Slate50)
                ) {

                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        androidx.compose.material.Text("Hello Android")
                        Button(onClick = {
                            statics.logged = false
                        }){
                            Text(text = "Log Out")
                        }
                    }

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