package com.vdcast.spendcalendar

import android.app.Application
import android.content.Context
import com.vdcast.spendcalendar.di.initKoin
import com.vdcast.spendcalendar.extensions.initLogs
import org.koin.dsl.module

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        initLogs()
        initKoin(appModule = module {
            single<Context> { this@App.applicationContext }
        })
        instance = this
    }

    companion object{
        lateinit var instance: App
    }
}