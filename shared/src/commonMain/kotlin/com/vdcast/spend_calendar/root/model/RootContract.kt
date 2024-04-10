package com.vdcast.spend_calendar.root.model

import com.vdcast.spend_calendar.base.BaseViewState
import com.vdcast.spend_calendar.common.ui.AppPrefs

class RootContract {

    data class RootState(
        val themeIsDark: Boolean,
        val firstDayIsMonday: Boolean,
        val selectedTab: AppTab
    ): BaseViewState{

        val appPrefs: AppPrefs
            get() = AppPrefs(firstDayIsMonday = firstDayIsMonday)

        companion object {
            val NONE = RootState(
                themeIsDark = false,
                firstDayIsMonday = true,
                selectedTab = AppTab.Events
            )
        }
    }

}