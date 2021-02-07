package com.example.api.services

import models.entities.LoginData
import models.entities.UserCreds
import models.requests.LoginRequest
import models.requests.SignUPRequest
import models.responses.MultipleArticleResponse
import models.responses.TagsResponse
import models.responses.articleResponse
import models.responses.userResponse
import retrofit2.Response
import retrofit2.http.*


interface ConduitAPI {

    @POST("users")
    suspend fun registerUser(
        @Body userCreds: SignUPRequest
    ):Response<userResponse>

    @POST("users/login")
    suspend fun loginUser(
            @Body loginData: LoginRequest
    ):Response<userResponse>

    @GET("articles/{slug}")
    suspend fun getArticlesBySlug(
            @Path("slug")slug:String
    ):Response<articleResponse>

    @GET("tags")
    suspend fun getTags(): Response<TagsResponse>

    @GET("articles")
    suspend fun getArticles(
        @Query("author") author:String?=null,
        @Query("favourited") favourited:String?=null,
        @Query("tag") tag:String?=null,

        ):Response<MultipleArticleResponse>
}