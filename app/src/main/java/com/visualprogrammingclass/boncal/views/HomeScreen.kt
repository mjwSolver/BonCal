package com.visualprogrammingclass.boncal.views

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
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
import com.visualprogrammingclass.boncal.components.BoncalGradientButton
import com.visualprogrammingclass.boncal.components.ImageCard
import com.visualprogrammingclass.boncal.components.TheCarbonFootprintWidget
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


//    val data = remember { mutableStateListOf<Dto>() }
//    LaunchedEffect(key1 = widgetData) {
    homeViewModel.getLatestAirQualityWidgetData()
//    }

    val widgetData: State<String?> = homeViewModel.airQualityWidget.observeAsState()
    Log.d("widgetData", "${widgetData.value}")

//    val scrollState = rememberScrollState()

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
//            .scrollable(state = scrollState, orientation = Orientation.Vertical)
    ) {

        Column(modifier = Modifier
            .verticalScroll(scrollState)
            .weight(1F, fill = false)){

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

            TheCarbonFootprintWidget(onPlusButtonClick = {
                navController.navigate(NavbarScreenChildren.Category.route)
            })

            Spacer(modifier = Modifier.padding(12.dp))

            Text(text = "Local Resources",
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
                SubcomposeAsyncImage (
                    model = ImageRequest.Builder(LocalContext.current)
                        .placeholder(R.drawable.ic_baseline_visibility_24)
                        .data(widgetData.value)
                        .error(R.drawable.ic_baseline_visibility_off_24)
                        .crossfade( true)
                        .build(),
                    contentDescription = "Profile Photo",
                    contentScale = ContentScale.Crop,
                    loading = { CircularProgressIndicator(
                        color = foregroundColor()
                    ) }
                )
            }

            BoncalGradientButton(text = "Get Latest Widget Data") {
                homeViewModel.getLatestAirQualityWidgetData()
            }

            Spacer(modifier = Modifier.padding(14.dp))

            Text(text = "GoGreen by funding Reforestation Programs!",
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp,
                    fontFamily = Inter
                ),
                color = foregroundColor(),
            )

            Spacer(modifier = Modifier.padding(14.dp))
            // create a list here
            ImageCard(painter = painterResource(id = R.drawable.boncallogoblack)
                , contentDescription = "boncallogoblack", title = "Boncal Logo Black")

        }

    }
}

@Composable
@Preview
fun HomeScreenPreview(){
    HomeScreen(navController = rememberNavController())
}