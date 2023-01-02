package com.visualprogrammingclass.boncal.views

import android.content.Context
import android.util.Log
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.visualprogrammingclass.boncal.R
import com.visualprogrammingclass.boncal.components.TheCarbonFootprintWidget
import com.visualprogrammingclass.boncal.helpers.statics
import com.visualprogrammingclass.boncal.services.navigations.Screen
import com.visualprogrammingclass.boncal.viewModels.HomeViewModel
import com.visualprogrammingclass.boncal.views.ui.theme.BoncalGradient
import com.visualprogrammingclass.boncal.views.ui.theme.Slate50
import com.visualprogrammingclass.boncal.views.ui.theme.Slate900
import com.visualprogrammingclass.boncal.views.ui.theme.foregroundColor

@Composable
fun HomeScreen(
//    navController: NavController,
    context: Context = LocalContext.current,
    homeViewModel: HomeViewModel = hiltViewModel()
) {

    val widgetData: State<String?> = homeViewModel.widget.observeAsState()
//    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
//            .scrollable(state = scrollState, orientation = Orientation.Vertical)
    ) {

        TheCarbonFootprintWidget(onPlusButtonClick = { /*TODO*/ })

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
        
        homeViewModel.getLatestWidgetData()
        Button(onClick = { homeViewModel.getLatestWidgetData() }) {
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


//        Button(onClick = {
//            statics.logged = false
//        }){
//            Text(text = "Log Out - Don't press")
//        }
//        Button(onClick = { navController.navigate(Screen.Register.route)}) {
//            Text("Register route")
//        }