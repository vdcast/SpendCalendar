package com.vdcast.spendcalendar.settings.child.auth.child.register.model

import com.vdcast.spendcalendar.base.BaseEvent
import com.vdcast.spendcalendar.base.BaseViewState

class RegisterContract {
    data class State(
        val email: String,
        val password: String,
        val confirmPassword: String
    ) : BaseViewState {

        val sendIsActive: Boolean
            get() = password.isNotBlank() && confirmPassword.isNotBlank() && password == confirmPassword

        companion object {
            val NONE = State(
                email = "",
                password = "",
                confirmPassword = ""
            )
        }
    }

    sealed interface Event : BaseEvent {
        data object Success : Event
        data class Error(val message: String) : Event
    }
}