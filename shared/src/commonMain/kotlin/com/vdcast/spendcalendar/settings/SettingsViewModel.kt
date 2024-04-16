package com.vdcast.spendcalendar.settings

import com.vdcast.spendcalendar.base.BaseViewModel
import com.vdcast.spendcalendar.platform.DeviceInfo
import com.vdcast.spendcalendar.settings.SettingsContract.*
import com.vdcast.spendcalendar.storage.SettingsManager
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

//class SettingsViewModel : BaseViewModel<State, Nothing>() {
//
//    init {
//
//        SettingsManager.themeIsDarkFlow.onEach {
//            updateState { copy(themeIsDark = it) }
//        }.launchIn(viewModelScope)
//
////        val deviceInfo = DeviceInfo()
//
//        updateState {
//            copy(deviceInfo = deviceInfo.getSummary())
//        }
//    }
//
//    override fun initialState() = State.NONE
//
//    fun switchTheme(isDark: Boolean) {
//        SettingsManager.themeIsDark = isDark
//    }
//
//
//}

class SettingsViewModel(
    private val deviceInfo: DeviceInfo,
    private val settingsManager: SettingsManager,
    private val categoriesRepository: CategoriesRepository,
    private val eventsRepository: EventsRepository,
    private val api: AppApi
) : BaseViewModel<State, Event>() {

    init {
        bindToEmail()
        bindToTheme()
        bindToToken()
        initDeviceInfo()
    }

    fun switchTheme(isDark: Boolean) {
        settingsManager.themeIsDark = isDark
    }

    fun sync() = viewModelScope.launch(
        CoroutineExceptionHandler { _, t ->
            appLog(t.stackTraceToString())
            pushEvent(Event.Error(t.message.orEmpty()))
        }
    ) {
        updateState { copy(isLoading = true) }
        delay(3000)
        syncCategories()
        syncEvens()
        pushEvent(Event.DataSynced)
        updateState { copy(isLoading = false) }
    }

    fun logout() {
        settingsManager.email = ""
        settingsManager.token = ""
    }

    //*********** region private *************

    private suspend fun syncCategories() {
        val apiCategories = categoriesRepository.getAll().map { it.toApi() }
        val categoriesSyncResponse = api.syncCategories(apiCategories)
        if (categoriesSyncResponse.status.isSuccess()) {
            val remoteCategories = categoriesSyncResponse.body<List<CategoryApi>>()
            categoriesRepository.insertAll(remoteCategories.map(CategoryApi::toEntity))
        }
    }

    private suspend fun syncEvens() {
        val apiEvents = eventsRepository.getAll().map { it.toApi() }
        val eventsSyncResponse = api.syncEvents(apiEvents)
        if (eventsSyncResponse.status.isSuccess()) {
            val remoteEvents = eventsSyncResponse.body<List<SpendEventApi>>()
            eventsRepository.insertAll(remoteEvents.map { it.toEntity() })
        }
    }

    private fun bindToEmail() {
        settingsManager.emailFlow.onEach { email ->
            updateState { copy(email = email) }
        }.launchIn(viewModelScope)
    }

    private fun bindToToken() {
        settingsManager.tokenFlow.onEach { token ->
            updateState { copy(isAuth = token.isNotBlank()) }
        }.launchIn(viewModelScope)
    }

    private fun initDeviceInfo() {
        updateState {
            copy(info = deviceInfo.getSummary())
        }
    }

    private fun bindToTheme() {
        settingsManager.themeIsDarkFlow.onEach {
            updateState { copy(themeIsDark = it) }
        }.launchIn(viewModelScope)
    }

    override fun initialState() = State.NONE

    //endregion

}