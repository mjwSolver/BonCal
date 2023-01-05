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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.visualprogrammingclass.boncal.components.BoncalGradientButton
import com.visualprogrammingclass.boncal.viewModels.ProfileViewModel
import com.visualprogrammingclass.boncal.views.ui.theme.foregroundColor

@Composable
fun ProfileScreen(
    context: Context = LocalContext.current,
    profileViewModel: ProfileViewModel = hiltViewModel()
){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        
        Text(
            text = "Profile",
            style = MaterialTheme.typography.displayLarge
        )
        
        BoncalGradientButton(text = "Logout",) {
//            profileViewModel.logOut()
        }




        Button(
            onClick = {
//                profileViewModel.deleteStoredUserData()
                Toast.makeText(
                    context,
                    "User Data Erased",
                    Toast.LENGTH_SHORT).show()
            },
            colors = ButtonDefaults.buttonColors(
                contentColor = foregroundColor()
            )
        ){
            Text(text = "Delete Stored User Data")
        }

        Button(
            onClick = {
//                profileViewModel.deleteStoredUserToken()
                Toast.makeText(
                    context,
                    "User Token Erased",
                    Toast.LENGTH_SHORT).show()
            },
            colors = ButtonDefaults.buttonColors(
                contentColor = foregroundColor()
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
                contentColor = foregroundColor()
            )
        ){
            Text(text = "Execute Onboarding after Restart")
        }


        
        
    }
}

@Composable
@Preview
fun ProfileScreenPreview() {
    ProfileScreen()
}