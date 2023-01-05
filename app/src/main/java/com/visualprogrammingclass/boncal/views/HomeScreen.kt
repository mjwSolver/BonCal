package com.visualprogrammingclass.boncal.views

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.visualprogrammingclass.boncal.R
import com.visualprogrammingclass.boncal.components.TheCarbonFootprintWidget
import com.visualprogrammingclass.boncal.services.navigations.main.NavbarScreenChildren
import com.visualprogrammingclass.boncal.viewModels.HomeViewModel
import com.visualprogrammingclass.boncal.views.ui.theme.Slate50
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

        Text(text = "Daily Carbon Calculator",
            style = MaterialTheme.typography.displayMedium,
            color = foregroundColor(),
        )

        Spacer(modifier = Modifier.padding(12.dp))

        TheCarbonFootprintWidget(onPlusButtonClick = {
            navController.navigate(NavbarScreenChildren.Category.route)
        })

        Spacer(modifier = Modifier.padding(14.dp))

        Text(text = "Useful Resources",
            style = MaterialTheme.typography.displayMedium,
            color = foregroundColor(),
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
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
        }


        homeViewModel.getLatestAirQualityWidgetData(context)

        Button(onClick = { homeViewModel.getLatestAirQualityWidgetData(context) }) {
            Text(text = "Get Latest Widget Data")
        }
        Button(onClick = { homeViewModel.getUserToken() }) {
            Text(text = "Get User Token")
        }

        ImageCard(painter = painterResource(id = R.drawable.boncallogoblack)
            , contentDescription = "boncallogoblack", title = "Boncal Logo Black")

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

@Composable
fun ImageCard(
    painter: Painter,
    contentDescription: String,
    title: String,
    modifier: Modifier = Modifier
){
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        elevation = 5.dp
    ) {
        Box(modifier = Modifier.height(200.dp))
        {
            Image(
                painter = painter,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            )
//                                            startY = 300f
                        )
                    )
            ) {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                    contentAlignment = Alignment.BottomStart
                ){
                    Text(title, style = TextStyle(color = Slate50,fontSize = 16.sp))
                }
            }
        }
    }
}

@Composable
@Preview
fun HomeScreenPreview(){
    HomeScreen(navController = rememberNavController())
}