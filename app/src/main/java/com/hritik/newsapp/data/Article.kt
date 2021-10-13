package com.hritik.newsapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "articles")
data class Article(
        @PrimaryKey val author: String,
        val content: String,
        val description: String,
        val publishedAt: String,
        val title: String,
        val url: String,
        val urlToImage: String
)