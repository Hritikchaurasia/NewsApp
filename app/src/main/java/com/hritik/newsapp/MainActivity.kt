package com.hritik.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import com.hritik.newsapp.Adapter.NewsAdapter
import com.hritik.newsapp.databinding.ActivityMainBinding
import com.hritik.newsapp.view.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val newsAdapter = NewsAdapter()
        viewModel.articles.observe(this@MainActivity) {
            newsAdapter.differ.submitList(it.data)
        }

        binding.apply {
            recyclerView.setHasFixedSize(true)
            recyclerView.itemAnimator = null
            recyclerView.adapter = newsAdapter
        }


}}