package com.example.condiut.ui.articles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.CondiutClient
import kotlinx.coroutines.launch
import models.entities.Article
import models.entities.ArticleData
import models.requests.CreateArticleRequest

class ArticleViewModel : ViewModel() {

    val Api = CondiutClient.api
    val authApi = CondiutClient.authApi
    private val _article = MutableLiveData<Article>()
    val article: LiveData<Article> = _article


    fun fetchArticle(slug: String) = viewModelScope.launch {
        val resp = Api
            .getArticlesBySlug(slug)
        resp.body()?.article.let {
            _article.postValue(it)
        }

    }

    fun createArticle(
        title: String,
        description: String,
        body: String,
        tags: List<String>
    ) = viewModelScope.launch {
        val resp = authApi.createArticles(
            CreateArticleRequest(
                ArticleData(
                    body,
                    description,
                    tags,
                    title
                )
            )
        )
        resp.body()?.article.let {
            _article.postValue(it)
        }
    }
}