package com.example.condiut.ui.articles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.CondiutClient
import kotlinx.coroutines.launch
import models.entities.Article

class ArticleViewModel:ViewModel() {

    val authApi = CondiutClient.api

    private val _article = MutableLiveData<Article>()
    val article:LiveData<Article> = _article


    fun fetchArticle(slug:String)=  viewModelScope.launch {
        val resp = authApi.getArticlesBySlug(slug)
        resp.body()?.article.let {
            _article.postValue(it)
        }

    }
}