package com.visualprogrammingclass.boncal.models.authentication

import com.google.gson.Gson
import com.visualprogrammingclass.boncal.helpers.JsonConvertible

// API -> Kotlin
data class User(
    val CreatedAt: String,
    val DeletedAt: Any,
    val Email: String,
    val ID: Int,
    val Name: String,
    val PersonalAccessToken: Any,
    val UpdatedAt: String
): JsonConvertible()