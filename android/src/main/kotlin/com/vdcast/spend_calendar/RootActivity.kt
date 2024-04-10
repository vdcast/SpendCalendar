package com.vdcast.spend_calendar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.vdcast.spend_calendar.root.RootScreen
import com.vdcast.spend_calendar.root.RootViewModel

class RootActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RootScreen(RootViewModel())
//            SayHelloFromCommon()

        }
    }
}