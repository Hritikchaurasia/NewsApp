package com.hritik.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.hritik.newsapp.Adapter.NewsAdapter
import com.hritik.newsapp.databinding.ActivityMainBinding
import com.hritik.newsapp.utils.SwipeGesture
import com.hritik.newsapp.view.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import androidx.work.ExistingWorkPolicy

import androidx.work.WorkManager

import com.hritik.newsapp.utils.ProcessingWorker

import androidx.work.OneTimeWorkRequest

import android.R.string.no





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

        val swipeGesture = object :SwipeGesture(){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                when(direction){
                    ItemTouchHelper.LEFT ->{
                        Log.d("swipe","left")
                    }
                    ItemTouchHelper.RIGHT ->{
                        Log.d("swipe","left")

                    }

                }

                super.onSwiped(viewHolder, direction)
            }
        }

        viewModel.isLoading.observe(this@MainActivity){
            if(it){
                Snackbar.make(binding.root, "Loading new data ....", Snackbar.LENGTH_SHORT).show();

            }
        }


        //
        val refreshWork = OneTimeWorkRequest.Builder(ProcessingWorker::class.java).build()
        WorkManager.getInstance(applicationContext)
            .enqueueUniqueWork(getString(R.string.LOCAL_NOTIFICATION), ExistingWorkPolicy.KEEP, refreshWork)



}}