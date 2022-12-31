package com.visualprogrammingclass.boncal.services.retrofit

import android.content.Context
import android.util.Log
import com.visualprogrammingclass.boncal.helpers.Const
import com.visualprogrammingclass.boncal.repositories.DataStoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun getRetrofitServiceInstance(retrofit: Retrofit): EndPointAPI {
        return retrofit.create(EndPointAPI::class.java)
    }

    @Singleton
    @Provides
    fun getRetrofitInstance(): Retrofit {

        val logging = HttpLoggingInterceptor { message ->
            Log.d("OkHttp", message)
        }

        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        return Retrofit
            .Builder().client(client)
            .baseUrl(Const.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideDataStoreRepository(
        @ApplicationContext context: Context
    ) = DataStoreRepository(context = context)
}