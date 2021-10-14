package com.hritik.newsapp.repository

import androidx.lifecycle.viewModelScope
import androidx.room.withTransaction
import com.hritik.newsapp.api.NewsApi
import com.hritik.newsapp.data.Article
import com.hritik.newsapp.database.ArticleDatabase
import com.hritik.newsapp.utils.networkBoundResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val api: NewsApi,
    private val db: ArticleDatabase
) {
    private val articleDao = db.getArticleDao()

    suspend fun deleteArticle(articleId: Int) : Unit{
        articleDao.deleteArticle(articleId)
    }

    suspend fun readArticle(articleId: Int) : Unit{

            articleDao.updateArticle(articleId, true)

    }

    fun getArticles() = networkBoundResource(
        query = {
            articleDao.getAllArticles()
        },
        fetch = {
            getData()

        },
        saveFetchResult = { articles ->
            db.withTransaction {
                articleDao.deleteAllArticles()
                articleDao.insertArticle(articles)
            }
        }
    )

    suspend fun getData(): List<Article> {
        val cDate = Date()
        val today: String = SimpleDateFormat("yyyy-MM-dd").format(cDate)
        var list = listOf<Article>()
        val cal = Calendar.getInstance()
        cal.add(Calendar.DATE, -1)
        val yesterday: String = SimpleDateFormat("yyyy-MM-dd").format(cal.time)


        val news = api.getNews(
            searchQuery = "india",
            from = today,
            to = today
        )
        if (news.totalResults < 50) {
            val oldNews = api.getNews(
                searchQuery = "india",
                from = yesterday,
                to = yesterday
            )

            var tempList = mutableListOf<Article>()
            tempList.addAll(news.articles)
            tempList.addAll(oldNews.articles)
            list = tempList
            return list
        } else {
            list = news.articles
            return list
        }


    }
}