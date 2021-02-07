package com.example.condiut.data

import com.example.api.CondiutClient

object ArticlesRepo {
    val api = CondiutClient().api
    suspend fun getGlobalFeed()= api.getArticles()
}