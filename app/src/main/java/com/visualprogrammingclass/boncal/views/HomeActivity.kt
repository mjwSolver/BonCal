package com.visualprogrammingclass.boncal.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeActivity() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
//        Text(text = "Home", color = MaterialTheme.typography.h3.fontSize, fontWeight = FontWeight.Bold)
    }
}

@Composable @Preview
fun HomeScreenPreview(){
    HomeActivity()
}