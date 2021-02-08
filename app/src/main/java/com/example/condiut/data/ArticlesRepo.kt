package com.example.condiut.data

import com.example.api.CondiutClient

object ArticlesRepo {
    val api = CondiutClient.api
    val authApi = CondiutClient.authApi


    suspend fun getGlobalFeed()= api.getArticles()

    suspend fun getMyFeed()= authApi.getFeedArticles()

}