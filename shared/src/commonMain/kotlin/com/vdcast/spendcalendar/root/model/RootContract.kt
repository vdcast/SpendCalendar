package com.vdcast.spendcalendar.root.model

import com.vdcast.spendcalendar.base.BaseViewState
import com.vdcast.spendcalendar.common.ui.theme.AppPrefs

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