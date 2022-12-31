package com.visualprogrammingclass.boncal.models.authentication

data class RegisterDetail(
    val name: String,
    val email: String,
    val password: String,
    val repeat: String
)