package com.visualprogrammingclass.boncal.repositories

import com.visualprogrammingclass.boncal.models.authentication.LoginDetail
import com.visualprogrammingclass.boncal.models.authentication.RegisterDetail
import com.visualprogrammingclass.boncal.services.retrofit.EndPointAPI
import javax.inject.Inject


class EndPointRepository @Inject constructor(private val api: EndPointAPI) {

    // Register / Sign up = New User
    suspend fun registerUser(registerDetail: RegisterDetail) = api.registerUser(registerDetail)

    // Login-in
    suspend fun loginUser(loginDetail: LoginDetail) = api.loginUser(loginDetail)

    // Log-out
    suspend fun getUserData(token:String) = api.getUserData(token)


}