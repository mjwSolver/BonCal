package com.visualprogrammingclass.boncal.views

import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.SubcomposeAsyncImage
import com.visualprogrammingclass.boncal.components.*
import com.visualprogrammingclass.boncal.helpers.chrome
import com.visualprogrammingclass.boncal.models.article.ArticleResponseItem
import com.visualprogrammingclass.boncal.models.widgets.WidgetResponseItem
import com.visualprogrammingclass.boncal.services.navigations.main.NavbarScreenChildren
import com.visualprogrammingclass.boncal.viewModels.HomeViewModel
import com.visualprogrammingclass.boncal.views.ui.theme.*

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
        homeViewModel.getLatestWidgetData().invokeOnCompletion {
            Log.d("HomeScreen", "getLatestWidgetData() completed")
        }


        homeViewModel.saveUserDataWithToken().invokeOnCompletion {
            Log.d("HomeScreen", "saveUserDataWithToken() completed")
            homeViewModel.readUserDataAsClass().invokeOnCompletion {
                Log.d("HomeScreen", "readUserDataAsClass() completed")
            }
        }
    }

//    homeViewModel.getLatestWidgetData()

    val widgets: State<ArrayList<WidgetResponseItem>?> = homeViewModel.widgets.observeAsState()
//    val allWidgetImage: State<List<String>?> = homeViewModel.widgetImage.observeAsState(initial = null)
//    val allWidgetUrl: State<List<String>?> = homeViewModel.widgetUrl.observeAsState(initial = null)

    Log.d("HomeScreen", "WidgetImage is ${widgets.value?.get(0)?.image}")
    Log.d("HomeScreen", "WidgetURL is ${widgets.value?.get(0)?.image}")

    widgets.value?.let { theWidgets ->
        Log.d("HomeScreen", "WidgetImage after is ${theWidgets.get(0).image}")
        Log.d("HomeScreen", "WidgetUrl after is ${theWidgets.get(0).data}")
    }

    val userEmission: State<Double> = homeViewModel.userEmission.observeAsState(initial = 0.0)
    val articles: State<ArrayList<ArticleResponseItem>?> = homeViewModel.articles.observeAsState()

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(2.dp)
//            .scrollable(state = scrollState, orientation = Orientation.Vertical)
    ) {

        Column(
//            modifier = Modifier
//                .verticalScroll(scrollState)
//                .weight(1F, fill = false)
        ) {

//            item {
            Spacer(modifier = Modifier.padding(10.dp))

            Text(
                modifier = Modifier.padding(start = 12.dp),
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
            Box(modifier = Modifier.padding(start = 12.dp, end = 12.dp)) {
                TheCarbonFootprintWidget(
                    onPlusButtonClick = {
                        navController.navigate(NavbarScreenChildren.Category.route)
                    },
                    carbonKg = floatedValue.toString()
                )
            }

            Spacer(modifier = Modifier.padding(12.dp))

            Text(
                modifier = Modifier.padding(start = 12.dp),
                text = "Local Resources",
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    fontFamily = Inter
                ),
                color = foregroundColor(),
            )

            Spacer(modifier = Modifier.padding(4.dp))

            Row(
//                    modifier = Modifier.size(100.dp),
//                modifier = Modifier.background(Rose400)
            ) {

//                    widgets.value?.let { createWidgetList(widgets = it) }
                LazyRow(
                    contentPadding = PaddingValues(all = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
////                    modifier = Modifier.fillMaxSize().background(Indigo600).wrapContentSize()
                ) {

                    widgets.value?.let { listOfWidgets: ArrayList<WidgetResponseItem> ->
                        items(count = listOfWidgets.size) { index ->

                            WidgetIconClickable(
                                imageUrl = listOfWidgets[index].image,
                                linkUrl = listOfWidgets[index].data, //data is URL
                            )


                        }

                    }

                }

            }

            Spacer(modifier = Modifier.padding(4.dp))

            Text(
                modifier = Modifier.padding(start = 12.dp),
                text = "Articles",
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    fontFamily = Inter
                ),
                color = foregroundColor(),
            )

            Spacer(modifier = Modifier.padding(2.dp))
            // create a list here

            LazyRow(
                contentPadding = PaddingValues(all = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
//                modifier = Modifier.background(Rose400)
            ) {

                articles.value?.let { listOfArticles ->
                    items(count = listOfArticles.size) { index ->

                        val theTitle = if (listOfArticles[index].Title.length > 30) {
                            "${listOfArticles[index].Title.take(30)}..."
                        } else {
                            listOfArticles[index].Title
                        }

                        ArticleItemClickable(
                            title = theTitle,
                            imageUrl = listOfArticles[index].CoverImage,
                            linkUrl = listOfArticles[index].Url,
                        )
                    }

                }

            }

//                Spacer(Modifier.padding(10.dp))
//
//                Text(text = "something")
        }
//        } // item

    } // column
}

@Composable
@Preview
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}


