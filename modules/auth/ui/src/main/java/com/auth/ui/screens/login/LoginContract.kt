package com.auth.ui.screens.login

import com.auth.ui.screens.ViewEvent
import com.auth.ui.screens.ViewSideEffect
import com.auth.ui.screens.ViewState
import com.auth.domain.model.Login as LoginDomain

class LoginContract {
    sealed class Event : ViewEvent {
        data class Login(val login: LoginDomain) : Event()
        data object Register : Event()

    }

    data class State(
        val isLoading: Boolean,
        val isError: Boolean,
    ) : ViewState

    sealed class Effect : ViewSideEffect {
        //data object DataWasLoaded : Effect()

        sealed class Navigation : Effect() {
            data object ToRegister : Navigation()
        }
    }
}