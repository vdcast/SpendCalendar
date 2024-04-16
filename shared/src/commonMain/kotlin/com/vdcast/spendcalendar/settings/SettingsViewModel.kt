package com.vdcast.spendcalendar.settings

import com.vdcast.spendcalendar.base.BaseViewModel
import com.vdcast.spendcalendar.platform.DeviceInfo
import com.vdcast.spendcalendar.settings.SettingsContract.*
import com.vdcast.spendcalendar.storage.SettingsManager
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