package com.vdcast.spendcalendar.settings

import com.vdcast.spendcalendar.base.BaseViewState

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