package com.hritik.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
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
        Log.d("response", "test")

        val newsAdapter = NewsAdapter()
        viewModel.news.observe(this@MainActivity) {
            Log.d("response", it.size.toString())
            newsAdapter.differ.submitList(it)
        }

        binding.apply {
            recyclerView.setHasFixedSize(true)
            recyclerView.itemAnimator = null
            recyclerView.adapter = newsAdapter
        }


        viewModel.isLoading.observe(this@MainActivity) { loading ->

            if (loading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                newsAdapter.differ.submitList(viewModel.news.value)


                binding.progressBar.visibility = View.INVISIBLE
                val value = viewModel.news.value
//                Log.d("response" , value.toString())
            }


        }
    }
}