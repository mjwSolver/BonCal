package com.visualprogrammingclass.boncal.views

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.view.WindowManager
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.visualprogrammingclass.boncal.components.boncalRoundedShape
import com.visualprogrammingclass.boncal.services.navigations.main.NavbarScreenChildren
import com.visualprogrammingclass.boncal.viewModels.CategoryViewModel
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.visualprogrammingclass.boncal.R
import com.visualprogrammingclass.boncal.helpers.JsonConvertible.Companion.fromJson
import com.visualprogrammingclass.boncal.models.SingleAvailableEmissionType
import com.visualprogrammingclass.boncal.views.ui.theme.*

@ExperimentalFoundationApi
@Composable
fun CategoryScreen(
    navController: NavController,
    categoryViewModel: CategoryViewModel = hiltViewModel()
) {
    // navigate - don't pop backstack

    val theList: State<List<SingleAvailableEmissionType>?> = categoryViewModel.list.observeAsState()
    categoryViewModel.getEmissionTypes()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(2.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(26.dp)
                .clip(boncalRoundedShape())
                .background(Blue200),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Let's count your carbon footprint now!",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = Inter,
                        color = Blue600
                    ),
                )
                Spacer(modifier = Modifier.padding(2.dp))
                Text(
                    text = "Be more conscious about your daily carbon output by counting it!",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = Inter,
                        color = Blue600
                    ),
                )
            }
        }

        Text(
            text = "Select your Emission Type",
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = Inter,
                color = foregroundColor()
            ),
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(10.dp),
            content = {
                theList.value?.let {
                    items(it.size) { i ->

                        val categoryInternalIndex = it[i].ID
                        val categoryAsString = it[i].toJson()

                        Box(
                            modifier = Modifier
                                .aspectRatio(1F)
                                .size(100.dp)
                                .background(
                                    color = Color(
                                        android.graphics.Color
                                            .parseColor("#${it[i].BackgroundColor.takeLast(6) }")
                                    )
                                )
                                .clip(boncalRoundedShape())
                                .padding(4.dp)
                                .clickable(onClick = {
                                    navController.navigate(
                                        route = NavbarScreenChildren.Calculate.passIdAndClass(
                                            categoryInternalIndex,
                                            categoryAsString
                                        )
                                    )
                                }),
                            contentAlignment = Alignment.CenterStart,
                        ) {

                            Column(
                                horizontalAlignment = Alignment.Start,
                                verticalArrangement = Arrangement.SpaceBetween,

                            ){
                                SubcomposeAsyncImage (
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .placeholder(R.drawable.ic_baseline_visibility_24)
                                        .data(it[i].Icon)
                                        .error(R.drawable.ic_baseline_visibility_off_24)
                                        .crossfade( true)
                                        .build(),
                                    contentDescription = "Icons",
                                    contentScale = ContentScale.Crop,
                                    loading = { CircularProgressIndicator(
                                        color = foregroundColor()
                                    ) }
                                )


                                Text(text = it[i].Name, style = TextStyle(
                                    color = Color(android.graphics.Color.parseColor("#${ it[i].ForegroundColor.takeLast(6) }"))
//                                color = Color(it[0].ForegroundColor.toLong())
                                ))
                                Text(text = "(${it[i].Unit})", style = TextStyle(
                                    color = Color(android.graphics.Color.parseColor("#${ it[i].ForegroundColor.takeLast(6) }"))
//                                color = Color(it[0].ForegroundColor.toLong())
                                ))
                            }

                        }

                    }
                }
            }
        )


    } // end of the column

}





fun Color.fromHex(color: String) = Color(android.graphics.Color.parseColor("#$color"))

@OptIn(ExperimentalFoundationApi::class)
@Composable
@Preview
fun CategoryScreenPreview() {
    CategoryScreen(rememberNavController())
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
@Preview(uiMode = UI_MODE_NIGHT_YES)
fun CategoryScreenDarkPreview() {
    CategoryScreen(rememberNavController())
}