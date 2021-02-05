package com.example.api.services

import models.entities.UserCreds
import models.requests.SignUPRequest
import models.responses.MultipleArticleResponse
import models.responses.userResponse
import retrofit2.Response
import retrofit2.http.Body

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface ConduitAPI {

    @POST("users")
    suspend fun registerUser(
        @Body userCreds: SignUPRequest
    ):Response<userResponse>

    @GET("articles")
    suspend fun getArticles(
        @Query("author") author:String?=null,
        @Query("favourited") favourited:String?=null,
        @Query("tag") tag:String?=null,

        ):Response<MultipleArticleResponse>
}