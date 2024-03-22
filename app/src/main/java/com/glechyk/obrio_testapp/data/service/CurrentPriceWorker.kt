package com.glechyk.obrio_testapp.data.service

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.glechyk.obrio_testapp.domain.usecase.UpdateCurrentPriceUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@HiltWorker
class CurrentPriceWorker @AssistedInject constructor(
    private val updateCurrentPriceUseCase: UpdateCurrentPriceUseCase,
    @Assisted private val context: Context,
    @Assisted private val params: WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO) {
            updateCurrentPriceUseCase()
            Result.success()
        }
    }
}