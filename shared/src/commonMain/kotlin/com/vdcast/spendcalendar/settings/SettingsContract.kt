package com.vdcast.spendcalendar.settings

import com.vdcast.spendcalendar.base.BaseEvent
import com.vdcast.spendcalendar.base.BaseViewState

class SettingsContract {
    data class State(
        val info: String,
        val themeIsDark: Boolean,
        val isAuth: Boolean,
        val isLoading: Boolean,
        val email: String
    ) : BaseViewState {
        companion object {
            val NONE = State(
                info = "",
                themeIsDark = false,
                isAuth = false,
                isLoading = false,
                email = ""
            )
        }
    }

    sealed interface Event : BaseEvent {
        data object DataSynced : Event
        data class Error(val message: String) : Event
    }
}