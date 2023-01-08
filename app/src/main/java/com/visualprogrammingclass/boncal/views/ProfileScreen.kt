package com.visualprogrammingclass.boncal.views

import android.content.Context
import android.graphics.Paint.Align
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.visualprogrammingclass.boncal.components.BoncalGradientButton
import com.visualprogrammingclass.boncal.components.boncalRoundedShape
import com.visualprogrammingclass.boncal.viewModels.ProfileViewModel
import com.visualprogrammingclass.boncal.views.ui.theme.*

@Composable
fun ProfileScreen(
    context: Context = LocalContext.current,
    profileViewModel: ProfileViewModel = hiltViewModel()
) {

    val name: State<String> = profileViewModel.name.observeAsState(initial = "name")
    val createdAt: State<String> = profileViewModel.createdAt.observeAsState(initial = "createdAt")
    val email: State<String> = profileViewModel.email.observeAsState(initial = "email")
    val totalCarbonEmission: State<Double> =
        profileViewModel.totalCarbonEmission.observeAsState(0.00)

    profileViewModel.getUserData(context)

    Row(modifier = Modifier.padding(start = 12.dp, end = 12.dp)) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // BonCal Profile Slot
            Box(
                modifier = Modifier
                    .clip(boncalRoundedShape())
                    .background(foregroundColor())
                    .padding(20.dp)
                    .fillMaxWidth()
            ) {
                Row(
//                    modifier = Modifier.background(Rose300),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically,
                ) {

                    // Just a profile Icon

                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(50.dp)
                            .background(boncalGradient()),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Person,
                            contentDescription = "Person Icon}",
                            tint = Slate50
                        )
                    }

                    Spacer(modifier = Modifier.padding(4.dp))

                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = name.value,
                            style = TextStyle(
                                color = backgroundColor(),
                                fontSize = 20.sp
                            )
                        )

                        Text(
                            text = email.value,
                            style = TextStyle(
                                color = backgroundColor(),
                                fontSize = 16.sp
                            )
                        )
                    }

                }

            }

            Spacer(modifier = Modifier.padding(8.dp))

            BoncalGradientButton(text = "Logout") {
                profileViewModel.logOut()
                Toast.makeText(context, "You're logged out", Toast.LENGTH_SHORT).show()
            }

        }
    }
}

@Composable
@Preview
fun ProfileScreenPreview() {
    ProfileScreen()
}