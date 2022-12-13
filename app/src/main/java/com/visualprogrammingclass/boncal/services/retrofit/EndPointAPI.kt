package com.visualprogrammingclass.boncal.services.retrofit

import com.visualprogrammingclass.boncal.models.*
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface EndPointAPI {

//    @POST("/auth/register")
//    suspend fun createNewUser(
//        @Body registerRequest: RequestBody
//    ): Response<ApiResponse<>>

//    @GET("/users/{email}")
////    suspend fun getOneUser(
////        @Query("email") email: String
////    ): Response<ApiResponse<>>

    @POST("/auth/login")
    suspend fun loginUser(
        @Body loginDetails: LoginDetail
    ):Response<ApiResponse<LoginResponse>>

//    @GET("/auth/user")
//    @GET("/auth/user/logout")

    @GET("/api/emission/list-categories")
    suspend fun getListOfCategories(
        @Header("Authorization") auth:String
    ): Response<ApiResponse<DataListEmissionCategory>>

    @POST("/api/emission/log-emission")
    suspend fun logEmission(
        @Body EmissionBody:String,
        @Header("Authorization") auth:String
    ): Response<ApiResponse<DataDetailedEmissionLogResponse>>



//    @GET("movie/now_playing")
//    suspend fun getNowPlaying(
//        @Query("api_key") apiKey:String,
//        @Query("language") language:String,
//        @Query("page") page:Int
//    ): Response<NowPlaying>

//    @GET("movie/{movie_id}")
//    suspend fun getMovieDetails(
//        @Path("movie_id") Id:Int,
//        @Query("api_key") apiKey:String
//    ):Response<MovieDetails>

}