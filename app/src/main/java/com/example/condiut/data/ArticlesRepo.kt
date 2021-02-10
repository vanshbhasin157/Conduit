package com.example.condiut.data

import com.example.api.CondiutClient
import models.entities.ArticleData
import models.requests.CreateArticleRequest

object ArticlesRepo {
    val api = CondiutClient.api
    val authApi = CondiutClient.authApi


    suspend fun getGlobalFeed() = api.getArticles()

    suspend fun getMyFeed() = authApi.getFeedArticles()

    suspend fun createArticle(
        title: String,
        description: String,
        body: String,
        tags: List<String>
    ) = authApi.createArticles(
        CreateArticleRequest(ArticleData(body, description, tags, title))
    )

}