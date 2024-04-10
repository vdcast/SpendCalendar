package com.vdcast.spend_calendar.root

import com.vdcast.spend_calendar.base.BaseViewModel
import com.vdcast.spend_calendar.root.RootContract.State
import com.vdcast.spend_calendar.storage.SettingsManager
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class RootViewModel : BaseViewModel<State, Nothing>() {

    init {
        SettingsManager.themeIsDarkFlow.onEach { isDark ->
            updateState { copy(themeIsDark = isDark) }
        }.launchIn(viewModelScope)
    }

    override fun initialState() = State.NONE
}