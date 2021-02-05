package com.example.api.services

import models.MultipleArticleResponse
import retrofit2.Call

import retrofit2.http.GET


interface ConduitAPI {
    @GET("articles")
    fun getArticles():Call<MultipleArticleResponse>
}