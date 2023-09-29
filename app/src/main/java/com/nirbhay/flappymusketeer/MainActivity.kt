package com.nirbhay.flappymusketeer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.nirbhay.flappymusketeer.common.Analytics
import com.nirbhay.flappymusketeer.common.Analytics.logEvent
import com.nirbhay.flappymusketeer.common.Events

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logEvent(Events.APP_OPEN)
        setContent {
            App()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        logEvent(Events.APP_CLOSED)
        Analytics.firebaseAnalytics = null
    }
}

