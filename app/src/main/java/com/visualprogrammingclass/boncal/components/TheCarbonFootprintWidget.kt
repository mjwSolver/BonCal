package com.visualprogrammingclass.boncal.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.visualprogrammingclass.boncal.R
import com.visualprogrammingclass.boncal.views.ui.theme.*

@Composable
fun boncalRoundedShape():Shape {
    return RoundedCornerShape(
        corner = CornerSize(10.dp)
    )
}

@Composable
fun TheCarbonFootprintWidget() {
    Row(
        modifier = Modifier
            .background(backgroundColor())
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {

        Box(
            modifier = Modifier
                .clip(boncalRoundedShape())
                .background(Teal500)
                .padding(18.dp),
            contentAlignment = Alignment.Center,
        ){

            Box(
                modifier = Modifier.size(50.dp, 50.dp),
                contentAlignment = Alignment.Center
            ){
                Image(
                    modifier = Modifier.size(120.dp, 120.dp),
                    painter = painterResource(id = R.drawable.boncallogolight),
                    contentScale = ContentScale.Crop,
                    contentDescription = "boncallogolight"
                )
            }

        }

        Box(
            modifier = Modifier
                .padding(4.dp)
                .background(Sky500)
                .padding(18.dp)
        ){
            Column() {
                Text(
                    text = "body large",
                    fontFamily = Inter
                )
            }
        }

        Box(
            modifier = Modifier.padding(4.dp)
        ){

            Box(modifier = Modifier
                .clip(boncalRoundedShape())
                .background(Sky500)
                .padding(10.dp))

            Column(
                modifier = Modifier
            ) {
                //Icon
                // Text Count

            }
            Text(text = "The Plus Icon is here")
        }

    }
}

@Composable
@Preview
fun CarbonFootprintWidgetPreview(){
    TheCarbonFootprintWidget()
}

@Composable
@Preview(uiMode = UI_MODE_NIGHT_YES)
fun CarbonFootprintWidgetDarkPreview(){
    TheCarbonFootprintWidget()
}