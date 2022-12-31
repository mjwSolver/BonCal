package com.visualprogrammingclass.boncal.views.onboarding

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.pager.*
import com.visualprogrammingclass.boncal.components.BoncalGradientButton
import com.visualprogrammingclass.boncal.services.navigations.Screen
import com.visualprogrammingclass.boncal.viewModels.WelcomeViewModel
import com.visualprogrammingclass.boncal.views.ui.theme.*

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun WelcomeScreen(
    navController: NavHostController,
    welcomeViewModel: WelcomeViewModel = hiltViewModel()) {

    val pagerState = rememberPagerState()
    val theColor = if(isSystemInDarkTheme()) Slate50 else Slate900

    Column(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            modifier = Modifier.weight(10f),
            count = 3,
            state = pagerState,
            verticalAlignment = Alignment.Top
        ) { position ->
            PagerScreen(onBoardingPage = welcomeViewModel.pages[position])
        }
        HorizontalPagerIndicator(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .weight(1f),
            activeColor = theColor,
            pagerState = pagerState
        )
        FinishButton(
            modifier = Modifier.weight(1f),
            pagerState = pagerState
        ) {
            welcomeViewModel.saveOnBoardingState(completed = true)
            navController.popBackStack()
            navController.navigate(Screen.Login.route)
        }
    }
}

@Composable
fun PagerScreen(onBoardingPage: OnBoardingPage) {

    val textColor = if(isSystemInDarkTheme()) Slate50 else Slate900

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .fillMaxHeight(0.7f),
            painter = painterResource(id = onBoardingPage.image),
            contentDescription = "Pager Image"
        )
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = onBoardingPage.title,
            fontSize = MaterialTheme.typography.h4.fontSize,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = textColor,
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
                .padding(top = 20.dp),
            text = onBoardingPage.description,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            color = textColor,
        )
    }
}

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun FinishButton(
    modifier: Modifier,
    pagerState: PagerState,
    onClick: () -> Unit
) {

    Row(
        modifier = modifier
            .padding(horizontal = 40.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(
            modifier = Modifier
                .padding(2.dp)
                .fillMaxWidth(),
            visible = pagerState.currentPage == 2
        ) {

//            Button(
//                onClick = onClick,
//                colors = ButtonDefaults.buttonColors(
//                    contentColor = Color.White
//                )
//            ) {
//                Text(text = "Finish")
//            }

            Button(
                contentPadding = PaddingValues(),
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(
                    contentColor = Slate50,
                    backgroundColor = Color.Transparent
                ),
            ){
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(40.dp)
                        .background(BoncalGradient),
                    contentAlignment = Alignment.Center,
                ){
                    Text(text = "Finish")
                }
            }



//            BoncalGradientButton(
//                text = "Finish",
//                onClick = onClick)

        }
    }
}

@Composable
@Preview(showBackground = true)
fun FirstOnBoardingScreenPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        PagerScreen(onBoardingPage = OnBoardingPage.First)
    }
}

@Composable
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
fun SecondOnBoardingScreenPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        PagerScreen(onBoardingPage = OnBoardingPage.Second)
    }
}

@Composable
@Preview(showBackground = true)
fun ThirdOnBoardingScreenPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        PagerScreen(onBoardingPage = OnBoardingPage.Third)
    }
}

@OptIn(ExperimentalPagerApi::class, ExperimentalAnimationApi::class)
@Composable
@Preview(showBackground = true)
fun FinishButtonPreview() {

    val pagerState = rememberPagerState(2)

    Column(modifier = Modifier.fillMaxSize()) {
        FinishButton(
            modifier = Modifier.weight(1f),
            pagerState = pagerState
        ) {
        }
    }
}