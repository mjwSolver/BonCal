package com.visualprogrammingclass.boncal.repositories

import com.visualprogrammingclass.boncal.models.LoginDetail
import com.visualprogrammingclass.boncal.models.RegisterDetail
import com.visualprogrammingclass.boncal.models.userdata
import com.visualprogrammingclass.boncal.services.retrofit.EndPointAPI
import javax.inject.Inject


class RegisterRepository @Inject constructor(private val api: EndPointAPI) {

    // Register / Sign up = New User
    suspend fun registerUser(registerDetail: RegisterDetail) = api.registerUser(registerDetail)

    // Login-in
    suspend fun loginUser(loginDetail: LoginDetail) = api.loginUser(loginDetail)

    // Log-out


}