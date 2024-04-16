package com.vdcast.spendcalendar

import androidx.compose.ui.window.ComposeUIViewController
import com.vdcast.spendcalendar.root.RootViewModel
import com.vdcast.spendcalendar.root.compose.RootScreen

fun MainViewController() = ComposeUIViewController {
//    SayHelloFromCommon()
//    RootScreen(RootViewModel())
    RootScreen()
}