package com.visualprogrammingclass.boncal.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.visualprogrammingclass.boncal.views.ui.theme.*

@Composable
fun BoncalGradientButton(text:String, onClick:() -> Unit) {

    val theGradient = Brush.horizontalGradient(

        listOf(Blue400, Emerald400)
//        if (isSystemInDarkTheme()) listOf(Sky500, Teal500)
//        else listOf(Emerald400, Blue400)

    )

    Button(
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent
        ),
        onClick = onClick,
    ) {
        Box(
            modifier = Modifier
                .background(theGradient)
                .padding(horizontal = 12.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center){
            Text(text = text, color = Slate50)
        }
    }
}

@Composable
@Preview
fun GradientButtonPreview(){
    BonCalTheme() {
        Surface() {
//            BoncalGradientButton(modifier = Modifier.padding(5.dp), text = "Special") {
            BoncalGradientButton( text = "Special") { }
            }
        }
    }

@Composable
@Preview(uiMode = UI_MODE_NIGHT_YES)
fun GradientButtonPreviewDark(){
    BonCalTheme() {
        Surface() {
//            BoncalGradientButton(modifier = Modifier.padding(5.dp), text = "Special") {
                BoncalGradientButton( text = "Special") {
            }
        }
    }
}

@Composable
@Preview
fun NormalButton(){
    BonCalTheme() {
        Surface() {
           Button(onClick = { /*TODO*/ }) {
               Text(text = "String")
           }
        }
    }
}

