package com.hritik.newsapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "articles")
data class Article(
        @PrimaryKey(autoGenerate = true)
        val id:Int,
        val description: String,
        val publishedAt: String,
        val title: String,
        val urlToImage: String,
        var read: Boolean = false
)