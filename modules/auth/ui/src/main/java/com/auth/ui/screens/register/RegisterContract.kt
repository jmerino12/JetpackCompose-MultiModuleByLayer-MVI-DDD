package com.auth.ui.screens.register

import com.auth.domain.model.User
import com.auth.domain.model.Register as RegisterDomain
import com.auth.ui.screens.ViewEvent
import com.auth.ui.screens.ViewSideEffect
import com.auth.ui.screens.ViewState

class RegisterContract {
    sealed class Event : ViewEvent {
        data object BackButtonClicked : Event()
        data class Register(val register: RegisterDomain) : Event()

    }

    data class State(
        val user: User?,
        val isLoading: Boolean,
        val isError: Boolean,
    ) : ViewState

    sealed class Effect : ViewSideEffect {

        sealed class Navigation : Effect() {
            data object Back : Navigation()
        }
    }

}