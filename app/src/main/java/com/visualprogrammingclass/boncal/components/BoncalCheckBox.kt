package com.visualprogrammingclass.boncal.components

import androidx.compose.material3.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun BoncalCheckBox(
    checked: Boolean,
    checkChange: (Boolean) -> Unit
){
    Checkbox(checked = checked, onCheckedChange = checkChange)
}