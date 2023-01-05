package com.visualprogrammingclass.boncal.views

import android.content.Context
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.visualprogrammingclass.boncal.components.BoncalGradientButton
import com.visualprogrammingclass.boncal.components.BoncalTextInputLayout
import com.visualprogrammingclass.boncal.components.boncalRoundedShape
import com.visualprogrammingclass.boncal.models.CarbonEmissionDetail
import com.visualprogrammingclass.boncal.services.navigations.main.NavbarScreen
import com.visualprogrammingclass.boncal.views.ui.theme.Blue200
import com.visualprogrammingclass.boncal.views.ui.theme.foregroundColor


@Composable
fun BubbleAndTitle(
    title: String,
    bubbleMessage: String
){

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ){

        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            color = foregroundColor()
        )

        Box(
            modifier = Modifier
                .clip(boncalRoundedShape())
                .background(Blue200)
                .padding(4.dp)
                .fillMaxWidth()
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = bubbleMessage,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }

}

@Composable
fun CalculateScreen(
    navController: NavController,
    calculateId: String,
    about: String = "About Text",
    metrics: String = "Metric Text",
    unitSuffix:String = " kWh",
    context: Context = LocalContext.current,
//    calculateViewModel: CalculateViewModel = hiltViewModel()
) {
    // Store: Amount
    // Receive: ID

    // navigate back to home
    // if they navigate out and back, it'll only take them to home
    // wasted memory...
    
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // bubble about
        BubbleAndTitle(
            title = "About",
            bubbleMessage = "this is the about")

        // bubble metrics tracked
        BubbleAndTitle(
            title = "Metrics tracked",
            bubbleMessage = "this is the info")

        // text input layout

        val (emission, setEmission) = rememberSaveable { mutableStateOf("") }
        BoncalTextInputLayout(
            label = "kwh",
            leadIcon = Icons.Default.AddCircle,
            text = emission,
            setText = setEmission,
            onClick = {setEmission("")}
        )

        // button calculate


        BoncalGradientButton(text = "Calculate") {
            if(emission.toIntOrNull() is Int){
                val carbonEmissionDetail = CarbonEmissionDetail(
                    amount = emission.toInt(),
                    emission_id = calculateId.toInt(),
                )

//                calculateViewModel.sendingCarbonEmissionData(
//                    carbonEmissionDetail
//                )

                navController.popBackStack()
                navController.navigate(NavbarScreen.HomeNB.route)
            } else {
                Toast.makeText(context, "Invalid value", Toast.LENGTH_SHORT).show()
            }
        }


    }
}

@Composable
@Preview
fun CalculateScreenPreview() {
    CalculateScreen(rememberNavController(), "1")
}

@Composable
@Preview(uiMode = UI_MODE_NIGHT_YES)
fun CalculateScreenDarkPreview() {
    CalculateScreen(rememberNavController(), "1")
}