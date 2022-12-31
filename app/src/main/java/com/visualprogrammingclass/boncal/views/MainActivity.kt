package com.visualprogrammingclass.boncal.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.visualprogrammingclass.boncal.services.navigations.SetupNavGraph
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