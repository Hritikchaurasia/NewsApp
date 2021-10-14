package com.hritik.newsapp.view

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.hritik.newsapp.api.NewsApi
import com.hritik.newsapp.data.Article
import com.hritik.newsapp.data.NewsResponse
import com.hritik.newsapp.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
   private val repository: NewsRepository
) :ViewModel() {
    val articles = repository.getArticles().asLiveData()
    val isLoading : MutableLiveData<Boolean> = MutableLiveData(false);

    init {
        setLoading()
    }
    private fun setLoading(){
        viewModelScope.launch {
            delay(2000L)
            isLoading.value = true
            delay(2000L)
            isLoading.value = false
        }
    }

    fun deleteArticle(articleId: Int){
        viewModelScope.launch {
            repository.deleteArticle(articleId)

        }
    }

    fun readArticle(articleId: Int){
        viewModelScope.launch {
            repository.readArticle(articleId)
        }
    }

}