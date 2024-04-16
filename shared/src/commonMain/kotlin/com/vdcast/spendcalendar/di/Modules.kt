@file:OptIn(ExperimentalSerializationApi::class)

package com.vdcast.spendcalendar.di

import com.vdcast.spendcalendar.categories.CategoriesRepository
import com.vdcast.spendcalendar.categories.list.CategoriesViewModel
import com.vdcast.spendcalendar.categories.model.CategoryDao
import com.vdcast.spendcalendar.common.ui.calendar.DatePickerViewModel
import com.vdcast.spendcalendar.db.AppDb
import com.vdcast.spendcalendar.events.EventsRepository
import com.vdcast.spendcalendar.events.create.CreateEventViewModel
import com.vdcast.spendcalendar.events.list.EventsViewModel
import com.vdcast.spendcalendar.events.model.SpendEventDao
import com.vdcast.spendcalendar.extensions.appLog
import com.vdcast.spendcalendar.network.AppApi
import com.vdcast.spendcalendar.network.DateSerializer
import com.vdcast.spendcalendar.network.DateTimeSerializer
import com.vdcast.spendcalendar.platform.DeviceInfo
import com.vdcast.spendcalendar.root.RootViewModel
import com.vdcast.spendcalendar.settings.SettingsViewModel
import com.vdcast.spendcalendar.settings.child.auth.child.register.RegisterViewModel
import com.vdcast.spendcalendar.settings.child.auth.child.signin.SignInViewModel
import com.vdcast.spendcalendar.storage.DbAdapters
import com.vdcast.spendcalendar.storage.SettingsManager
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.QualifierValue
import org.koin.dsl.module
import org.koin.ext.getFullName

object CoreModule {
    val deviceInfo = module {
        single { DeviceInfo() }
        factory { Dispatchers.Default + SupervisorJob() }
    }
}

object NetworkModule {
    val json = module {
        single {
            Json {
                encodeDefaults = false
                isLenient = true
                ignoreUnknownKeys = true
                explicitNulls = false
                prettyPrint = true
                serializersModule = SerializersModule {
                    contextual(LocalDateTime::class, DateTimeSerializer)
                    contextual(LocalDate::class, DateSerializer)
                }
            }
        }
    }

    val httpClient = module {
        single {
            HttpClient(CIO) {
                expectSuccess = false
                install(ContentNegotiation) {
                    json(get())
                }
                install(Logging) {
                    level = LogLevel.ALL
                    logger = object : Logger {
                        override fun log(message: String) {
                            appLog(message)
                        }
                    }
                }
            }
        }
    }

    val api = module { single { AppApi(get(), get()) } }
}

object StorageModule {
    val settings = module {
        single { SettingsManager(get()) }
    }
    val db = module {
        single {
            AppDb(get(), DbAdapters.categoryDbAdapter, DbAdapters.eventDbAdapter)
        }
    }
    val dao = module {
        single { CategoryDao(get(), get()) }
        single { SpendEventDao(get(), get()) }
    }
}

object RepositoriesModule {
    val repositories = module {
        single { CategoriesRepository(get()) }
        single { EventsRepository(get()) }
    }
}

object ViewModelsModule {
    val viewModels = module {
        single { RootViewModel(get()) }
        single { SettingsViewModel(get(), get(), get(), get(), get()) }
        single(DatePickerSingleQualifier) { DatePickerViewModel() }
        factory(DatePickerFactoryQualifier) { DatePickerViewModel() }
        factory { EventsViewModel(get(), get()) }
        single { CategoriesViewModel(get()) }
        factory { CreateEventViewModel() }
        factory { RegisterViewModel(get(), get()) }
        factory { SignInViewModel(get(), get()) }
    }
}

object DatePickerSingleQualifier : Qualifier {
    override val value: QualifierValue
        get() = this::class.getFullName()
}

object DatePickerFactoryQualifier : Qualifier {
    override val value: QualifierValue
        get() = this::class.getFullName()
}