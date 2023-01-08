package com.visualprogrammingclass.boncal.components

import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.visualprogrammingclass.boncal.helpers.chrome
import com.visualprogrammingclass.boncal.views.ui.theme.Slate50
import com.visualprogrammingclass.boncal.views.ui.theme.Slate900

@Composable
fun BoncalClickableText(title:String, onClick: ()->Any){

    val offset = Offset(2.0f, 3.0f)
    ClickableText(
        text = AnnotatedString(title),
        onClick = {
            onClick
        },
        style = TextStyle(
            color = Slate50, fontSize = 16.sp,
            shadow = Shadow(
                color = Slate900,
                offset = offset)
            )
        )

}