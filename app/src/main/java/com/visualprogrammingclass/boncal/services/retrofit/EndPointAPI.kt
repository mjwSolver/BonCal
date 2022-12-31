package com.visualprogrammingclass.boncal.services.retrofit

import com.visualprogrammingclass.boncal.models.*
import com.visualprogrammingclass.boncal.models.authentication.*
import com.visualprogrammingclass.boncal.models.widgets.WidgetResponse
import retrofit2.Response
import retrofit2.http.*

// ============
// template Heading
// ============

interface EndPointAPI {

    // ============
    // Authentication and Administration
    // ============

    @POST("/auth/register")
    suspend fun registerUser(
        @Body registerResponse: RegisterDetail
    ): Response<ApiResponse<RegisterResponse>>

    @POST("/auth/login")
    suspend fun loginUser(
        @Body loginDetails: LoginDetail
    ):Response<ApiResponse<LoginResponse>>

    @GET("/auth/user")
    suspend fun getUserData(
        @Header("Authorization") token:String
    ): Response<ApiResponse<User>>

    // ============
    // Widgets
    // ============

    @GET("/api/my-widgets")
    suspend fun getWidgetData(
        @Header("Authorization") token: String
    ): Response<ApiResponse<WidgetResponse>>

    // ============
    // ...
    // ============

    @GET("/api/emission/list-categories")
    suspend fun getListOfCategories(
        @Header("Authorization") token:String
    ): Response<ApiResponse<DataListEmissionCategory>>

    @POST("/api/emission/log-emission")
    suspend fun logEmission(
        @Body EmissionBody:String,
        @Header("Authorization") token:String
    ): Response<ApiResponse<DataDetailedEmissionLogResponse>>


}