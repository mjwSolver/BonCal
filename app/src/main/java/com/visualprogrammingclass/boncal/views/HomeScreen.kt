package com.visualprogrammingclass.boncal.views

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.visualprogrammingclass.boncal.R
import com.visualprogrammingclass.boncal.components.*
import com.visualprogrammingclass.boncal.helpers.chrome
import com.visualprogrammingclass.boncal.models.article.ArrayListOfArticleResponse
import com.visualprogrammingclass.boncal.models.article.ArticleResponseItem
import com.visualprogrammingclass.boncal.services.navigations.main.NavbarScreen
import com.visualprogrammingclass.boncal.services.navigations.main.NavbarScreenChildren
import com.visualprogrammingclass.boncal.viewModels.HomeViewModel
import com.visualprogrammingclass.boncal.views.ui.theme.Inter
import com.visualprogrammingclass.boncal.views.ui.theme.foregroundColor

@Composable
fun HomeScreen(
    navController: NavController,
    context: Context = LocalContext.current,
    homeViewModel: HomeViewModel = hiltViewModel()
) {

    SideEffect {
        homeViewModel.getArticles().invokeOnCompletion() {
            Log.d("HomeScreen", "getArticles() completed")
        }
//        homeViewModel.getLatestAirQualityWidgetData().invokeOnCompletion() {
//            Log.d("HomeScreen", "getLatestAirQualityWidgetData() completed")
//        }
        homeViewModel.getLatestWidgetData().invokeOnCompletion() {
            Log.d("HomeScreen", "getLatestWidgetData() completed")
        }


        homeViewModel.saveUserDataWithToken().invokeOnCompletion {
            Log.d("HomeScreen", "saveUserDataWithToken() completed")
            homeViewModel.readUserDataAsClass().invokeOnCompletion {
                Log.d("HomeScreen", "readUserDataAsClass() completed")
            }
        }
    }

//    val data = remember { mutableStateListOf<Dto>() }
//    LaunchedEffect(key1 = widgetData) {
    homeViewModel.getLatestWidgetData()
//    }
    val allWidgetData: State<List<String>?> = homeViewModel.allWidget.observeAsState(initial = null)
    val allWidgetUrl: State<List<String>?> = homeViewModel.widgetUrl.observeAsState(initial = null)

    Log.d("allWidgetData", "${allWidgetData.value}")

    val userEmission: State<Double> = homeViewModel.userEmission.observeAsState(initial = 0.0)
    val articles: State<ArrayList<ArticleResponseItem>?> = homeViewModel.articles.observeAsState()
//    homeViewModel.getArticles()

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
//            .scrollable(state = scrollState, orientation = Orientation.Vertical)
    ) {

        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .weight(1F, fill = false)
        ) {

            Spacer(modifier = Modifier.padding(10.dp))

            Text(
                text = "Daily Carbon Calculator",
                style =
                TextStyle(
                    fontWeight = FontWeight.Medium,
                    fontSize = 24.sp,
                    fontFamily = Inter
                ),
                color = foregroundColor(),
            )

            Spacer(modifier = Modifier.padding(10.dp))

            val floatedValue = (Math.round(userEmission.value * 10.0) / 10.0)
            TheCarbonFootprintWidget(
                onPlusButtonClick = {
                    navController.navigate(NavbarScreenChildren.Category.route)
                },
                carbonKg = floatedValue.toString()
            )

            Spacer(modifier = Modifier.padding(12.dp))

            Text(
                text = "Local Resources",
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    fontFamily = Inter
                ),
                color = foregroundColor(),
            )

            Spacer(modifier = Modifier.padding(2.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {

                // Prepare for loop

                LazyRow(
                    contentPadding = PaddingValues(all = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    allWidgetData.value?.let { arrayListResponse ->
                        items(count = arrayListResponse.size) { index ->

//                            CoilIconImage(
//                                imageUrl = arrayListResponse[index].Url,
//                                contentDescription = "boncallogoblack",
//                                title = arrayListResponse[index].Url
//                            )

                            CoilIconImageClickable(
                                imageUrl = arrayListResponse[index],
                                contentDescription = "",
                                title = "",
                                onclick = {
                                    chrome.openTab(context, allWidgetUrl.value?.get(index)!!)
                                }
                            )

                        }

                    }

                }

                Spacer(modifier = Modifier.padding(14.dp))

                Text(
                    text = "Articles",
                    style = TextStyle(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 24.sp,
                        fontFamily = Inter
                    ),
                    color = foregroundColor(),
                )

                Spacer(modifier = Modifier.padding(14.dp))
                // create a list here

                LazyRow(
                    contentPadding = PaddingValues(all = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    articles.value?.let { arrayListResponse ->
                        items(count = arrayListResponse.size) { index ->

                            CoilIconImage(
                                imageUrl = arrayListResponse[index].Url,
                                contentDescription = "boncallogoblack",
                                title = arrayListResponse[index].Url
                            )
                        }

                    }

                }

            }

        }
    }
}

@Composable
@Preview
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}
