package com.vdcast.spendcalendar.storage

import android.content.Context
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings
import com.vdcast.spendcalendar.App

actual class AppSettings actual  constructor() {
    actual val settings: Settings = SharedPreferencesSettings(
        App.instance.getSharedPreferences("AppSettings", Context.MODE_PRIVATE)
    )
}