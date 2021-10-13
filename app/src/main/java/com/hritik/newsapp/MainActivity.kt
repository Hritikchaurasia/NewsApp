package com.hritik.newsapp

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import com.hritik.newsapp.databinding.ActivityMainBinding
import com.hritik.newsapp.view.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        Log.d("response" , "test")
        viewModel.isLoading.observe(this@MainActivity){ loading ->

            if(loading){
                binding.progressBar.visibility = View.VISIBLE
            }else{
                binding.progressBar.visibility = View.INVISIBLE
                val value = viewModel.news.value

                    Log.d("response" , value.toString())
//                val now: Instant = Instant.now()
//                val yesterday: Instant = now.minus(1, ChronoUnit.DAYS)
//                val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")
//                Log.d("response" , now.toString() )
//                Log.d("response" , dateFormat.format(yesterday).toString() )

            }

        }
    }
}