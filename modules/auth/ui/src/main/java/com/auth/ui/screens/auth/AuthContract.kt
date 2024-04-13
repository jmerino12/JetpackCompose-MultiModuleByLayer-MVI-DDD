package com.auth.ui.screens.auth


import com.auth.ui.screens.ViewEvent
import com.auth.ui.screens.ViewSideEffect
import com.auth.ui.screens.ViewState

enum class AuthState { AUTHENTICATED, UNAUTHENTICATED }

class AuthContract {
    sealed class Event : ViewEvent
    data class State(
        val authState: AuthState,
        val isLoading: Boolean,
    ) : ViewState

    sealed class Effect : ViewSideEffect {
        sealed class Navigation : Effect()
    }
}