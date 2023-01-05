package com.visualprogrammingclass.boncal.components

import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Composable
fun boncalRoundedShape(): Shape {
    return RoundedCornerShape(
        corner = CornerSize(10.dp)
    )
}