package com.visualprogrammingclass.boncal.models.authentication

import com.visualprogrammingclass.boncal.helpers.JsonConvertible

// Kotlin -> API
data class LoginDetail(
    val email:String,
    val password:String,
    val remember:Boolean
):JsonConvertible()
