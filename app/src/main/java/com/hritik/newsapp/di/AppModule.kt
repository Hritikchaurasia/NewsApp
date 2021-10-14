package com.hritik.newsapp.di

import android.app.Application
import androidx.room.Room
import com.hritik.newsapp.api.NewsApi
import com.hritik.newsapp.database.ArticleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(NewsApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideNewsApi(retrofit: Retrofit) : NewsApi = retrofit.create(NewsApi::class.java)


    @Provides
    @Singleton
    fun provideDatabase(app: Application) : ArticleDatabase =
        Room.databaseBuilder(app, ArticleDatabase::class.java, "articles_database")
            .build()
}

