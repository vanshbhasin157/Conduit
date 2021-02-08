package com.example.api

import com.example.api.services.ConduitAPI
import com.example.api.services.ConduitAuthApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object CondiutClient {
     var authToken: String? = null

    private val authIntercepter = Interceptor { chain ->
        var req = chain.request()
        authToken?.let {
            req = req.newBuilder()
                .header("Authorization", "Token $it")
                .build()
        }
        chain.proceed(req)
    }

    val okHttpBuilder = OkHttpClient.Builder()
        .readTimeout(5, TimeUnit.SECONDS)
        .connectTimeout(2, TimeUnit.SECONDS)

    val retrofitBuilder = Retrofit.Builder()
        .baseUrl("https://conduit.productionready.io/api/")
        .addConverterFactory(MoshiConverterFactory.create())


    val api = retrofitBuilder
        .client(okHttpBuilder.build())
        .build()
        .create(ConduitAPI::class.java)
    val authApi = retrofitBuilder
        .client(okHttpBuilder.addInterceptor(authIntercepter).build())
        .build()
        .create(ConduitAuthApi::class.java)
}