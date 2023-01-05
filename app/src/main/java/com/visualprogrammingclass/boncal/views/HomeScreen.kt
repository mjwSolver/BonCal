package com.visualprogrammingclass.boncal.views

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.visualprogrammingclass.boncal.R
import com.visualprogrammingclass.boncal.components.TheCarbonFootprintWidget
import com.visualprogrammingclass.boncal.services.navigations.main.NavbarScreenChildren
import com.visualprogrammingclass.boncal.viewModels.HomeViewModel
import com.visualprogrammingclass.boncal.views.ui.theme.foregroundColor

@Composable
fun HomeScreen(
    navController: NavController,
    context: Context = LocalContext.current,
    homeViewModel: HomeViewModel = hiltViewModel()
) {

    val widgetData: State<String?> = homeViewModel.airQualityWidget.observeAsState()
//    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
//            .scrollable(state = scrollState, orientation = Orientation.Vertical)
    ) {

        TheCarbonFootprintWidget(onPlusButtonClick = {
            navController.navigate(NavbarScreenChildren.Category.route)
        })

        Text(text = "Welcome to the HomeScreen",
            color = foregroundColor())

        SubcomposeAsyncImage (
            model = ImageRequest.Builder(LocalContext.current)
                .placeholder(R.drawable.ic_baseline_visibility_24)
                .data(stringResource(id = R.string.widget))
                .error(R.drawable.ic_baseline_visibility_off_24)
                .crossfade( true)
                .build(),
            contentDescription = "Profile Photo",
            contentScale = ContentScale.Crop,
            loading = { CircularProgressIndicator(
                color = foregroundColor()
            ) }
        )
        
        homeViewModel.getLatestAirQualityWidgetData(context)
        Button(onClick = { homeViewModel.getLatestAirQualityWidgetData(context) }) {
            Text(text = "Get Latest Widget Data")
        }

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
        Log.d("widgetData", "${widgetData.value}")

    }
}