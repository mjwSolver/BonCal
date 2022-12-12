package com.visualprogrammingclass.boncal.repositories

import com.visualprogrammingclass.boncal.models.LoginDetail
import com.visualprogrammingclass.boncal.models.userdata
import com.visualprogrammingclass.boncal.retrofit.EndPointAPI
import javax.inject.Inject


class RegisterRepository @Inject constructor(private val api: EndPointAPI) {

    // Register / Sign up = New User
//    suspend fun registerUser(userData: userdata) = api.createNewUser(userData)

    // Login-in
    suspend fun loginUser(loginDetail: LoginDetail) = api.loginUser(loginDetail)

    // Log-out

}