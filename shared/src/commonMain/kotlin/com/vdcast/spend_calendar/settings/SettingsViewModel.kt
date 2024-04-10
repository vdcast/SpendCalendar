package com.vdcast.spend_calendar.settings

import com.vdcast.spend_calendar.base.BaseViewModel
import com.vdcast.spend_calendar.platform.DeviceInfo
import com.vdcast.spend_calendar.settings.SettingsContract.*
import com.vdcast.spend_calendar.storage.SettingsManager
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class SettingsViewModel : BaseViewModel<State, Nothing>() {

    init {

        SettingsManager.themeIsDarkFlow.onEach {
            updateState { copy(themeIsDark = it) }
        }.launchIn(viewModelScope)

        val deviceInfo = DeviceInfo()
        updateState {
            copy(deviceInfo = deviceInfo.getSummary())
        }
    }

    override fun initialState() = State.NONE

    fun switchTheme(isDark: Boolean) {
        SettingsManager.themeIsDark = isDark
    }


}