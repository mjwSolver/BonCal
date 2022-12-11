package com.visualprogrammingclass.boncal.models

data class LoginResponse(
    val expires_at: String,
    val id: Int,
    val name: String,
    val token: String,
    val user_id: Int
)