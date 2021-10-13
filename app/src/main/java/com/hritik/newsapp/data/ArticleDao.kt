package com.hritik.newsapp.data

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface ArticleDao {

    @Query("SELECT * FROM articles")
    fun getAllRestaurants(): Flow<List<Article>>

}