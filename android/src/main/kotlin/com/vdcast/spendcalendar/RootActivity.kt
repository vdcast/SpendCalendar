package com.vdcast.spendcalendar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.vdcast.spendcalendar.root.compose.RootScreen
import com.vdcast.spendcalendar.root.RootViewModel

class RootActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RootScreen(RootViewModel())
//            SayHelloFromCommon()

        }
    }
}