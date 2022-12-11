package com.visualprogrammingclass.boncal.models

data class ApiResponse <T> (
    val success: Boolean,
    val message: String,
    val data: T
)
