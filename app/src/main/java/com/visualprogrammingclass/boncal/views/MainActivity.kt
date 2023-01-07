package com.visualprogrammingclass.boncal.views

import android.os.Bundle
import android.util.Log
import android.view.WindowInsets.Side
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.visualprogrammingclass.boncal.services.navigations.BoncalNavbar
import com.visualprogrammingclass.boncal.services.navigations.SetupNavGraph
import com.visualprogrammingclass.boncal.services.navigations.main.NavbarScreen
import com.visualprogrammingclass.boncal.services.navigations.main.SetupNavBarGraph
import com.visualprogrammingclass.boncal.viewModels.SplashViewModel
import com.visualprogrammingclass.boncal.views.ui.theme.BonCalTheme
import com.visualprogrammingclass.boncal.views.ui.theme.Slate50
import com.visualprogrammingclass.boncal.views.ui.theme.Slate900
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@ExperimentalAnimationApi
@ExperimentalPagerApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this@MainActivity.actionBar?.hide()

//        installSplashScreen().setKeepOnScreenCondition {
//            !splashViewModel.isLoading.value
//        }

        setContent {

            BonCalTheme {

                // A surface container using the 'background' color from the theme
                Surface(

                    modifier = Modifier.fillMaxSize(),
                    color = UseColor(Slate900, Slate50)
                ) {

                    Log.d("MainActivity", "onCreate: ${splashViewModel.isLoading.value}")

                    val systemUiController = rememberSystemUiController()
                    val darkTheme = isSystemInDarkTheme()
                    SideEffect {
                        systemUiController.setSystemBarsColor(
                            color = if (darkTheme) Slate900 else Slate50
                        )
                    }

                    val screen by splashViewModel.startDestination
                    SetupNavGraph(
                        navController = rememberNavController(),
                        startDestination = screen
                    )

                }
            }
        }
    }
}