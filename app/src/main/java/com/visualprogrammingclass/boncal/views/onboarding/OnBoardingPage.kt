package com.visualprogrammingclass.boncal.views.onboarding

import com.visualprogrammingclass.boncal.R
import com.visualprogrammingclass.boncal.R.drawable

sealed class OnBoardingPage(
    val image: Int,
    val title: String,
    val description: String
) {
    object First : OnBoardingPage(
//        image = drawable.count,
        image = drawable.countsmall,
//        image = R.drawable.first,
        title = "Count",
        description = "Count your Carbon Emission Easily."
    )

    object Second : OnBoardingPage(
//        image = drawable.resources,
        image = drawable.resourcessmall,
//        image = R.drawable.second,
        title = "Learn",
        description = "Find Resources to reduce your carbon emission."
    )

    object Third : OnBoardingPage(
//        image = drawable.save,
        image = drawable.savesmall,
//        image = R.drawable.third,
        title = "Better Earth",
        description = "Low Carbon Footprint \n =\n Better Earth"
    )
}