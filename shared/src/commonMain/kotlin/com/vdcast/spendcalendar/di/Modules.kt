package com.vdcast.spendcalendar.di

import com.vdcast.spendcalendar.platform.DeviceInfo
import com.vdcast.spendcalendar.root.RootViewModel
import com.vdcast.spendcalendar.settings.SettingsViewModel
import com.vdcast.spendcalendar.storage.SettingsManager
import org.koin.dsl.module

object CoreModule {
    val deviceInfo = module  {
        single { DeviceInfo() }
    }
}

object StorageModule {
    val settings = module {
        single { SettingsManager(get()) }
    }
}

object ViewModelsModule{
    val viewModels = module {
        single { RootViewModel(get()) }
        factory { SettingsViewModel(get(), get()) }
    }
}