package com.hritik.newsapp.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface ArticleDao {

    @Query("SELECT * FROM articles")
    fun getAllArticles(): Flow<List<Article>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(articles: List<Article>)

    @Query("DELETE FROM articles")
    suspend fun deleteAllArticles()

    @Query("DELETE FROM articles WHERE id = :id")
    suspend fun deleteArticle(id: Int)

    @Query("UPDATE articles SET read = :isRead  WHERE id = :id")
    suspend fun updateArticle(id: Int , isRead:  Boolean)

}