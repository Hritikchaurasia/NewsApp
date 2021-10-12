package com.hritik.newsapp.api

import com.hritik.newsapp.R
import com.hritik.newsapp.data.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    companion object {
        const val BASE_URL = "https://newsapi.org"
        const val API_KEY = R.string.API_KEY.toString()
    }

    @GET("v2/everything")
    suspend fun getNews(
            @Query("q")
            searchQuery: String,
            @Query("from")
            from: String,
            @Query("to")
            to:String,
            @Query("apiKey")
            apiKey: String = API_KEY
    ) : NewsResponse

}