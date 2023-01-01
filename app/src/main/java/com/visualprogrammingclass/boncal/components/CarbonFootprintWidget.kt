package com.visualprogrammingclass.boncal.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.visualprogrammingclass.boncal.R
import com.visualprogrammingclass.boncal.views.ui.theme.backgroundColor

@Composable
fun FigmaFootprintWidget(){
    
}

@Composable
fun TheCarbonFootprintWidget() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {

        Box(
            modifier = Modifier
                .background(backgroundColor())
                .padding(6.dp),
            contentAlignment = Alignment.Center,
        ){
            Image(
                painter = painterResource(id = R.drawable.boncallogolight),
                contentDescription = "boncallogolight"
            )
        }

        Box(
            modifier = Modifier.padding(4.dp)
        ){

        }

        Box(
            modifier = Modifier.padding(4.dp)
        ){

        }

    }
}

@Composable
@Preview
fun CarbonFootprintWidgetPreview(){
    TheCarbonFootprintWidget()
}