package com.example.condiut.ui.feed

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.condiut.data.ArticlesRepo
import kotlinx.coroutines.launch
import models.entities.Article

class FeedViewModel: ViewModel() {


    private val _feed = MutableLiveData<List<Article>>()

    val feed: LiveData<List<Article>> = _feed

    fun fetchGlobalFeed() = viewModelScope.launch {
         ArticlesRepo.getGlobalFeed().body()?.let {
             _feed.postValue(it.articles)
             Log.d("feed", "feed fetched ${it.articlesCount}")
         }

    }

    fun fetchMyFeed() = viewModelScope.launch {
        ArticlesRepo.getMyFeed().body()?.let {
            _feed.postValue(it.articles)
        }
    }
}