package com.hritik.newsapp.utils

import android.content.Context
import android.util.Log
import androidx.work.*
import com.hritik.newsapp.repository.NewsRepository
import javax.inject.Inject


class ProcessingWorker
 @Inject constructor(private val repository: NewsRepository,context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    var currentContext: Context

    override fun doWork(): Result {
        try {
            Thread.sleep((60000 * 5).toLong())
            doTheActualProcessingWork()
        } catch (e: InterruptedException) {
            Log.d("PWLOG", "Thread sleep failed...")
            e.printStackTrace()
        }
        return Result.success()
    }

    private fun doTheActualProcessingWork() {
        repository.getArticles()
        val refreshWork = OneTimeWorkRequest.Builder(ProcessingWorker::class.java).build()
        WorkManager.getInstance(currentContext).enqueueUniqueWork(
            "Local_notification",
            ExistingWorkPolicy.REPLACE,
            refreshWork
        )
    }

    init {
        currentContext = context
    }
}