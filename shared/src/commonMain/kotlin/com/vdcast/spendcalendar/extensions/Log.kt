package com.vdcast.spendcalendar.extensions

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

fun appLog(message: String) {
    Napier.d("Spend Calendar: 🚀 $message")
}

fun initLogs() = Napier.base(DebugAntilog())