package com.visualprogrammingclass.boncal.models.authentication

data class RegisterResponse(
    val expires_at: String,
    val id: Int,                // ID dari Token
    val name: String,
    val token: String,
    val user_id: Int            // ID Usernya
)
