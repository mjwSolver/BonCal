package com.visualprogrammingclass.boncal.models.authentication

import com.google.gson.annotations.SerializedName

data class RegisterDetail(
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("repeat") val repeat: String
)