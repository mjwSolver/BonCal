package com.visualprogrammingclass.boncal.views.onboarding

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.visualprogrammingclass.boncal.R

sealed class OnBoardingPage(
    val image: Int,
    val title: String,
    val description: String
) {
    object First : OnBoardingPage(
        image = R.drawable.count,
//        image = R.drawable.first,
        title = "Count",
        description = "Count your Carbon Emission Easily."
    )

    object Second : OnBoardingPage(
        image = R.drawable.resources,
//        image = R.drawable.second,
        title = "Learn",
        description = "Find Resources to reduce your carbon emission."
    )

    object Third : OnBoardingPage(
        image = R.drawable.save,
//        image = R.drawable.third,
        title = "Better Earth",
        description = "Low Carbon Footprint \n =\n Better Earth"
    )
}