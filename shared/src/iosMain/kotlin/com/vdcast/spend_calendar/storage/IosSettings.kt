package com.vdcast.spend_calendar.storage

import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings
import platform.Foundation.NSUserDefaults

actual class AppSettings {
    actual val settings: Settings =  NSUserDefaultsSettings(NSUserDefaults.standardUserDefaults())
}