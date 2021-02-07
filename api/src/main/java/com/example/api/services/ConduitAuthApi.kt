package com.example.api.services

import models.requests.UpdateUserRequest
import models.responses.MultipleArticleResponse
import models.responses.articleResponse
import models.responses.profileResponse
import models.responses.userResponse
import retrofit2.Response
import retrofit2.http.*

interface ConduitAuthApi {

    @GET("user")
    suspend fun getCurrentUser():Response<userResponse>

    @PUT("user")
    suspend fun updateCurrentUser(
        @Body UpdateUserData: UpdateUserRequest
    ):Response<userResponse>
    @GET("profiles/{username}")
    suspend fun getProfile(
            @Path("username") username: String
    ): Response<profileResponse>

    @POST("profiles/{username}/follow")
    suspend fun followProfile(
            @Path("username") username: String
    ): Response<profileResponse>

    @DELETE("profiles/{username}/follow")
    suspend fun unfollowProfile(
            @Path("username") username: String
    ): Response<profileResponse>

    @GET("articles/feed")
    suspend fun getFeedArticles(): Response<MultipleArticleResponse>

    @POST("articles/{slug}/favorite")
    suspend fun favoriteArticle(
            @Path("slug") slug: String
    ): Response<articleResponse>

    @DELETE("articles/{slug}/favorite")
    suspend fun unfavoriteArticle(
            @Path("slug") slug: String
    ): Response<articleResponse>

}