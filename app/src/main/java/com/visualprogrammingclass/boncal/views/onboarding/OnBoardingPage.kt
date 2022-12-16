package com.visualprogrammingclass.boncal.views.onboarding

import androidx.annotation.DrawableRes
import com.visualprogrammingclass.boncal.R

sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String
)
    object First : OnBoardingPage(
        image = TODO(),
//        image = R.drawable.first,
        title = "Meeting",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod."
    )

    object Second : OnBoardingPage(
        image = TODO(),
//        image = R.drawable.second,
        title = "Coordination",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod."
    )

    object Third : OnBoardingPage(
        image = TODO(),
//        image = R.drawable.third,
        title = "Dialogue",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod."
    )
}