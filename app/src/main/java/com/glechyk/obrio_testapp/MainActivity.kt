package com.glechyk.obrio_testapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.glechyk.obrio_testapp.data.service.CurrentPriceWorker
import com.glechyk.obrio_testapp.presentation.navigation.MainNavHost
import com.glechyk.obrio_testapp.ui.theme.OBRIOtestappTheme
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var workManager: WorkManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OBRIOtestappTheme {
                MainNavHost()
            }
        }

        val periodicWorkRequest = PeriodicWorkRequest.Builder(
            CurrentPriceWorker::class.java,
            1,
            TimeUnit.HOURS
        ).build()

        workManager.enqueue(periodicWorkRequest)
    }
}