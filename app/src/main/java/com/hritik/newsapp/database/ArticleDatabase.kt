package com.hritik.newsapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hritik.newsapp.data.Article
import com.hritik.newsapp.data.ArticleDao


@Database(entities = [Article::class], version = 1)
abstract class RestaurantDatabase : RoomDatabase() {

    abstract fun articleDao(): ArticleDao
}

