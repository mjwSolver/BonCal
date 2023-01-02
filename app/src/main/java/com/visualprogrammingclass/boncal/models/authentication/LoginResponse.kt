package com.visualprogrammingclass.boncal.models.authentication

import com.visualprogrammingclass.boncal.helpers.JsonConvertible

// API -> Kotlin
data class LoginResponse(
    val expires_at: String,
    val id: Int,
    val name: String,
    val token: String,
    val user_id: Int
):JsonConvertible()



// API -> Kotlin
//data class LoginResponse(
//    val expires_at: String,
//    val id: Int,                // ID dari Token
//    val name: String,
//    val token_name: String,
//    val token: String,
//    val user: BasicUsernameEmailLogin,
//    val user_id: Int
//):JsonConvertible()