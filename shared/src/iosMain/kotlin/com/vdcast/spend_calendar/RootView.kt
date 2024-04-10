package com.vdcast.spend_calendar

import androidx.compose.ui.window.ComposeUIViewController
import com.vdcast.spend_calendar.root.RootViewModel
import com.vdcast.spend_calendar.root.compose.RootScreen

fun MainViewController() = ComposeUIViewController {
//    SayHelloFromCommon()
    RootScreen(RootViewModel())
}