package com.visualprogrammingclass.boncal.services.retrofit

import com.visualprogrammingclass.boncal.models.*
import com.visualprogrammingclass.boncal.models.article.ArrayListOfArticleResponse
import com.visualprogrammingclass.boncal.models.authentication.*
import com.visualprogrammingclass.boncal.models.safetyfirst.CarbonEmissionResponse
import com.visualprogrammingclass.boncal.models.widgets.ArrayListOfWidgetResponse
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
    ): Response<ArrayListOfWidgetResponse>


    // ============
    // Articles
    // ============

    @GET("/api/articles")
    suspend fun getArticles(
    ): Response<ArrayListOfArticleResponse>

    // ============
    // Reforestation
    // ============

    @GET("/api/reforestation-funds")
    suspend fun getReforestationFunds(
    ): Response<ReforestationListResponse>




    // ============
    // ...
    // ============

    // using list directly here
    @GET("/api/available-emmission-types")
    suspend fun getAvailableEmissionTypes(
        @Header("Authorization") token:String
    ): Response<ApiResponse<List<SingleAvailableEmissionType>>>

    @POST("/api/carbon-footprint")
    suspend fun sendCarbonEmissionData(
        @Header("Authorization") token:String,
        @Body carbonEmissionDetail: CarbonEmissionDetail
    ): Response<ApiResponse<CarbonEmissionResponse>>


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