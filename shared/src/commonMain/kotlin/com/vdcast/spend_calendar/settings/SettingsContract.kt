package com.vdcast.spend_calendar.settings

import com.vdcast.spend_calendar.base.BaseViewState

class SettingsContract {
    data class State(
        val deviceInfo: String,
        val themeIsDark: Boolean
    ) : BaseViewState {
        companion object {
            val NONE = State(
                deviceInfo = "",
                themeIsDark = false
            )
        }
    }
}