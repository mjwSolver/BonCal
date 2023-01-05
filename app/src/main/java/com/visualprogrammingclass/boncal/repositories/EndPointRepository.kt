package com.visualprogrammingclass.boncal.repositories

import com.visualprogrammingclass.boncal.models.CarbonEmissionDetail
import com.visualprogrammingclass.boncal.models.authentication.LoginDetail
import com.visualprogrammingclass.boncal.models.authentication.RegisterDetail
import com.visualprogrammingclass.boncal.services.retrofit.EndPointAPI
import javax.inject.Inject


class EndPointRepository @Inject constructor(private val api: EndPointAPI) {

    // Register / Sign up = New User
    suspend fun registerUser(registerDetail: RegisterDetail) = api.registerUser(registerDetail)

    // Login-in
    suspend fun loginUser(loginDetail: LoginDetail) = api.loginUser(loginDetail)

    // Get user data after login in or register
    suspend fun getUserData(token:String) = api.getUserData(token = "Bearer $token")

    // Getting WidgetData
    suspend fun getWidgetData(token:String) = api.getWidgetData(token = "Bearer $token")

    suspend fun getArticleData() = api.getArticles()

    suspend fun getEmissionTypes(token:String) = api.getAvailableEmissionTypes(token = "Bearer $token")

    suspend fun sendCarbonEmissionData(
        token:String,
        carbonFootPrintDetail:CarbonEmissionDetail
    ) = api.sendCarbonEmissionData(token = "Bearer $token", carbonFootPrintDetail)

}