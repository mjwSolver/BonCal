package com.visualprogrammingclass.boncal.models

data class loginrequest(
    val email: String,
    val name: String,
    val password: String,
    val repeat: String
)