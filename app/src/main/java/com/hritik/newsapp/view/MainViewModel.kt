package com.hritik.newsapp.view

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hritik.newsapp.api.NewsApi
import com.hritik.newsapp.data.Article
import com.hritik.newsapp.data.NewsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
   private val newsApi: NewsApi
) :ViewModel() {
    private val newsLiveData :  MutableLiveData<List<Article>> = MutableLiveData(listOf())
    val isLoading: MutableLiveData<Boolean> = MutableLiveData(true)
    val news: MutableLiveData<List<Article>> = newsLiveData
    init {
           getData()
    }

    fun getData(){
        val cDate = Date()
        val today: String = SimpleDateFormat("yyyy-MM-dd").format(cDate)

        val cal = Calendar.getInstance()
        cal.add(Calendar.DATE, -1)
        val yesterday: String = SimpleDateFormat("yyyy-MM-dd").format(cal.time)

        viewModelScope.launch {
            val news = newsApi.getNews(
                searchQuery = "india",
                from = today,
                to = today
            )
            if(news.totalResults < 50 ){
                val oldNews = newsApi.getNews(
                    searchQuery = "india",
                    from = yesterday,
                    to = yesterday
                )
                newsLiveData.value.let {
                    var data = mutableListOf<Article>()
                    data.addAll(it!!)
                    data.addAll(oldNews.articles)
                    newsLiveData.value = data
                    isLoading.value = false
                }


            }
            else{
                newsLiveData.value = news.articles
                isLoading.value = false
            }
        }
    }
}