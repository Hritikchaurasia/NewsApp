package com.hritik.newsapp.view

import androidx.lifecycle.ViewModel
import com.hritik.newsapp.api.NewsApi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    newsApi: NewsApi
) :ViewModel() {


}