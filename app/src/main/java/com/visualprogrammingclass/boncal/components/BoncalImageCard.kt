package com.visualprogrammingclass.boncal.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.visualprogrammingclass.boncal.R
import com.visualprogrammingclass.boncal.helpers.chrome
import com.visualprogrammingclass.boncal.views.ui.theme.*

// Base Copy-paste version
@Composable
fun ImageCard(
    painter: Painter,
    contentDescription: String,
    title: String,
    modifier: Modifier = Modifier
) {
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
                            ),
                            startY = 300f
                        )
                    )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp),
                    contentAlignment = Alignment.BottomStart
                ) {
                    Text(title, style = TextStyle(color = Slate50, fontSize = 16.sp))
                }
            }
        }
    }
}

// Status: Final
// Designed for: Widget

@Composable
fun WidgetIconClickable(
    imageUrl: String,
    linkUrl: String,
    modifier: Modifier = Modifier
) {

    val currentContext = LocalContext.current

    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = 5.dp
    ) {

        CoilSubComposeAsyncImage(link = imageUrl)

        Box(
            modifier = Modifier
                .size(90.dp, 80.dp)
                .clickable {
                    chrome.openTab(currentContext, linkUrl)
                    Log.d("BoncalImageCard", "Widget Box Pressed")
                }
            )

        }
    }

// Status: Final
// Designed for: Articles
@Composable
fun ArticleItemClickable(
    imageUrl: String,
    title: String,
    linkUrl:String,
    modifier: Modifier = Modifier
) {

    val currentContext = LocalContext.current

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        elevation = 5.dp
    ) {

        Box(
            modifier = Modifier.size(200.dp, 120.dp), // x , y
            contentAlignment = Alignment.BottomStart
        ) {

            CoilSubComposeAsyncImage(link = imageUrl)

            Box(
                modifier = Modifier
                    .padding(15.dp),
                contentAlignment = Alignment.BottomStart
            ) {

                BoncalClickableText(
                    title = title,
                    onClick = {
                        chrome.openTab(currentContext, linkUrl)
                    })
            }

            Box(
                modifier = Modifier
                    .size(90.dp, 80.dp)
                    .clickable {
                        chrome.openTab(currentContext, linkUrl)
                        Log.d("BoncalImageCard", "Article Box Pressed")
                    }
            )
        }
    }
}

@Composable
fun ReforestationItemClickable(
    imageUrl: String,
    title: String,
    linkUrl:String,
    modifier: Modifier = Modifier
) {

    val currentContext = LocalContext.current

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        elevation = 5.dp,
//        backgroundColor = Rose300
    ) {

        Box(
            modifier = Modifier
                .background(fadedBoncalGradient())
//                .background(Teal300)
                .size(200.dp, 90.dp), // x , y
            contentAlignment = Alignment.Center,

        ) {

            Row(
                modifier = Modifier
//                    .background(Amber200)
                    .padding(12.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(modifier = Modifier
                    .clip(boncalRoundedShape())
                    .size(70.dp, 70.dp),
//                    .background(Purple500),
                    propagateMinConstraints = true
                ) {
                    CoilSubComposeAsyncImage(link = imageUrl)
                }

                Spacer(modifier = Modifier.padding(4.dp))
                
                Column(
                    modifier = Modifier
//                        .background(Orange700)
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(text = title, style = TextStyle(color = foregroundColor(), fontSize = 16.sp))
                }

            }

            Box(
                modifier = Modifier
                    .size(200.dp, 120.dp)
                    .clickable {
                        chrome.openTab(currentContext, linkUrl)
                        Log.d("BoncalImageCard", "Reforestation Box Pressed")
                    }
            )
        }
    }
}

@Composable
fun CoilSubComposeAsyncImage(link:String){
    SubcomposeAsyncImage (
        model = ImageRequest.Builder(LocalContext.current)
            .placeholder(R.drawable.ic_baseline_visibility_24)
            .data(link)
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