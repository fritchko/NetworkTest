package com.example.networktest

import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object CatRepository {

    var catEndPoint: CatEndPoint? = null

    suspend fun networkCall(): Response<CatData>? {
        if (catEndPoint == null){
            catEndPoint = createRetrofitInstance().create(CatEndPoint::class.java)
        }

        return catEndPoint?.networkCall()
    }


    fun createRetrofitInstance(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://catfact.ninja/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder()
                .connectTimeout(30,TimeUnit.SECONDS)
                .readTimeout(30,TimeUnit.SECONDS)
                .writeTimeout(30,TimeUnit.SECONDS).build()).build()
    }

}