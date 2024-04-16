package com.vdcast.spendcalendar.root

import com.vdcast.spendcalendar.base.BaseViewModel
import com.vdcast.spendcalendar.root.model.AppTab
import com.vdcast.spendcalendar.root.model.RootContract
import com.vdcast.spendcalendar.storage.SettingsManager
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class RootViewModel(
    private val settingsManager: SettingsManager
) : BaseViewModel<RootContract.RootState, Nothing>() {

    init {
        settingsManager.themeIsDarkFlow.onEach {
            updateState { copy(themeIsDark = it) }
        }.launchIn(viewModelScope)
    }
    override fun initialState() = RootContract.RootState.NONE

    fun handleClickOnTab(appTab: AppTab) = updateState { copy(selectedTab = appTab) }
}