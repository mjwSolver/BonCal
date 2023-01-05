package com.visualprogrammingclass.boncal.views

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LiveData
import com.visualprogrammingclass.boncal.components.BoncalGradientButton
import com.visualprogrammingclass.boncal.viewModels.ProfileViewModel
import com.visualprogrammingclass.boncal.views.ui.theme.backgroundColor
import com.visualprogrammingclass.boncal.views.ui.theme.foregroundColor

@Composable
fun ProfileScreen(
    context: Context = LocalContext.current,
    profileViewModel: ProfileViewModel = hiltViewModel()
){

    val name: State<String> = profileViewModel.name.observeAsState(initial = "name")
    val createdAt: State<String> = profileViewModel.createdAt.observeAsState(initial = "createdAt")
    val email: State<String> = profileViewModel.email.observeAsState(initial = "email")
    val totalCarbonEmission: State<Double> = profileViewModel.totalCarbonEmission.observeAsState(0.00)

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        profileViewModel.getUserData(context)
        
        Text(
            text = "Profile: ${name.value}",
            style = MaterialTheme.typography.displayLarge
        )
        Text(
            text = "Email: ${email.value}",
            style = MaterialTheme.typography.displayLarge
        )
        Text(
            text = "Created at: ${createdAt.value}",
            style = MaterialTheme.typography.displayLarge
        )
        Text(
            text = "totalEmission: ${totalCarbonEmission.value}",
            style = MaterialTheme.typography.displayLarge
        )

        Button(
            onClick = {
                profileViewModel.deleteStoredUserData()
                Toast.makeText(
                    context,
                    "User Data Erased",
                    Toast.LENGTH_SHORT).show()
            },
            colors = ButtonDefaults.buttonColors(
                contentColor = backgroundColor(),
                containerColor = foregroundColor()
            )
        ){
            Text(text = "Delete Stored User Data")
        }

        Button(
            onClick = {
                profileViewModel.deleteStoredUserToken()
                Toast.makeText(
                    context,
                    "User Token Erased",
                    Toast.LENGTH_SHORT).show()
            },
            colors = ButtonDefaults.buttonColors(
                contentColor = backgroundColor(),
                containerColor = foregroundColor()
            )
        ){
            Text(text = "Delete Stored User Token")
        }

        Button(
            onClick = {
                profileViewModel.repeatOnboarding()
                Toast.makeText(
                    context,
                    "Onboarding will execute on next restart",
                    Toast.LENGTH_SHORT).show()
            },
            colors = ButtonDefaults.buttonColors(
                contentColor = backgroundColor(),
                containerColor = foregroundColor()
            )
        ){
            Text(text = "Execute Onboarding after Restart")
        }

        BoncalGradientButton(text = "Logout",) {
            profileViewModel.logOut()
            Toast.makeText(context, "You're logged out", Toast.LENGTH_SHORT).show()
        }



    }
}

@Composable
@Preview
fun ProfileScreenPreview() {
    ProfileScreen()
}