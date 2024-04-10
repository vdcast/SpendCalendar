package com.vdcast.spend_calendar.root

import com.vdcast.spend_calendar.base.BaseViewModel
import com.vdcast.spend_calendar.root.model.AppTab
import com.vdcast.spend_calendar.root.model.RootContract
import com.vdcast.spend_calendar.storage.SettingsManager
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class RootViewModel : BaseViewModel<RootContract.RootState, Nothing>() {

    init {
        SettingsManager.themeIsDarkFlow.onEach {
            updateState { copy(themeIsDark = it) }
        }.launchIn(viewModelScope)


    }
    override fun initialState() = RootContract.RootState.NONE

    fun handleClickOnTab(appTab: AppTab) = updateState { copy(selectedTab = appTab) }
}