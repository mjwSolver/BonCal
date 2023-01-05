package com.visualprogrammingclass.boncal.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.visualprogrammingclass.boncal.components.boncalRoundedShape
import com.visualprogrammingclass.boncal.services.navigations.main.NavbarScreenChildren
import com.visualprogrammingclass.boncal.viewModels.CategoryViewModel
import com.visualprogrammingclass.boncal.views.ui.theme.Blue200
import com.visualprogrammingclass.boncal.views.ui.theme.Blue400
import com.visualprogrammingclass.boncal.views.ui.theme.Blue600
import com.visualprogrammingclass.boncal.views.ui.theme.Teal500

@Composable
fun CategoryScreen(
    navController: NavController,
//    categoryViewModel: CategoryViewModel = hiltViewModel()
) {
    // navigate - don't pop backstack

//    val selectedEmissionTypeId = {
//        navController.navigate(
//            route = NavbarScreenChildren.Calculate.passId(
//                1
//            )
//        )
//    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(2.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

//        modifier = Modifier
//            .size(90.dp)
//            .clip(boncalRoundedShape())
//            .background(Teal500)
//            .padding(18.dp),
//        contentAlignment = Alignment.Center,
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
                .clip(boncalRoundedShape())
                .background(Blue200),
            contentAlignment = Alignment.Center
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Let's count your carbon footprint now!",
                    style = MaterialTheme.typography.titleSmall,
                    color = Blue600
                )
                Spacer(modifier = Modifier.padding(2.dp))
                Text(
                    text = "Be more conscious about your daily carbon output by counting it!",
                    style = MaterialTheme.typography.bodySmall,
                    color = Blue600
                )
            }
        }


    } // end of the column

}

@Composable
@Preview
fun CategoryScreenPreview(){
    CategoryScreen(rememberNavController())
}