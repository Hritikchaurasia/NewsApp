package com.hritik.newsapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hritik.newsapp.data.Article
import com.hritik.newsapp.data.ArticleDao
import dagger.Binds


@Database(entities = [Article::class], version = 1)
abstract class ArticleDatabase : RoomDatabase() {

    abstract fun getArticleDao(): ArticleDao


}

