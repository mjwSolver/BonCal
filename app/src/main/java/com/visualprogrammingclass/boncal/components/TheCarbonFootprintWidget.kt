package com.visualprogrammingclass.boncal.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.graphics.Paint.Align
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.visualprogrammingclass.boncal.R
import com.visualprogrammingclass.boncal.views.ui.theme.*
import io.sentry.Span

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

        // The Logo
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
                    modifier = Modifier.size(100.dp, 100.dp),
                    painter = painterResource(id = R.drawable.boncallogolight),
                    contentScale = ContentScale.Crop,
                    contentDescription = "boncallogolight"
                )
            }

        }

        // Carbon FootPrint
        Box(
            modifier = Modifier
                .clip(boncalRoundedShape())
                .padding(4.dp)
                .background(Blue200)
                .padding(18.dp)
        ){
            Column() {

                val baseStyle = TextStyle(
                    color = Blue600,
                    fontSize = 13.sp
                )
                val boldedStyle = TextStyle(
                    color = Blue600,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
                val subScriptStyle = TextStyle(
                    color = Blue600,
                    fontSize = 13.sp,
                    baselineShift = BaselineShift.Subscript
                )

                Text(text = "Carbon Footprint", style = baseStyle)

                Row(
                    modifier = Modifier
                ) {
                    Text(text = "100KG", style = boldedStyle)
                    Text(
                        fontSize = 13.sp,
                        text = buildAnnotatedString {
                            withStyle(
                                SpanStyle(
                                    fontSize = 16.sp,
                                    color = Blue600
                                )
                            ) {
                                append("CO")
                                Spacer(modifier = Modifier.padding(3.dp))
                                withStyle(
                                    SpanStyle(
                                        baselineShift = BaselineShift.Subscript,
                                        fontSize = 10.sp,
                                        color = Blue600
                                    )
                                ) {
                                    append("2")
                                }
                            }
                        }
                    )
                }



            }
        }

        // Carbon Add
        Box(
            modifier = Modifier
//                .size(50.dp, 50.dp)
                .padding(4.dp)
        ){

            Box(modifier = Modifier
                .clip(boncalRoundedShape())
                .background(Blue200)
                .padding(horizontal = 10.dp, vertical = 6.dp)){
                Column(
                    modifier = Modifier.padding(horizontal = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    IconButton(onClick = { TODO() } ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add Icon",
                            tint = Blue600
                        )
                    }

                    Text(
                        text = "Count",
                        color = Blue600
                    )
                }
            }

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