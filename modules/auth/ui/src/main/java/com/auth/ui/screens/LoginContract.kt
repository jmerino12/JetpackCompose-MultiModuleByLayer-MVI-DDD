package com.auth.ui.screens

import com.auth.domain.model.Login as LoginDomain

class LoginContract {
    sealed class Event : ViewEvent {
        data class Login(val login: LoginDomain) : Event()

    }

    data class State(
        val isLoading: Boolean,
        val isError: Boolean,
    ) : ViewState

    sealed class Effect : ViewSideEffect {
        object DataWasLoaded : Effect()

        sealed class Navigation : Effect() {
            data class ToRepos(val userId: String) : Navigation()
        }
    }
}