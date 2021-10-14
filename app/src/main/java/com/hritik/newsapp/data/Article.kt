package com.hritik.newsapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "articles")
data class Article(
        val description: String,
        val publishedAt: String,
        @PrimaryKey val title: String,
        val urlToImage: String
)