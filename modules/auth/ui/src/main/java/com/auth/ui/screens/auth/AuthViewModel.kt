package com.auth.ui.screens.auth

import androidx.lifecycle.viewModelScope
import com.auth.domain.repositories.AuthRepository
import com.auth.ui.screens.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(authRepository: AuthRepository) :
    BaseViewModel<AuthContract.Event, AuthContract.State, AuthContract.Effect>() {

    init {
        viewModelScope.launch {
            setState { copy(isLoading = true) }
            authRepository.authState
                .collect { user ->
                    if (user != null) {
                        setState { copy(authState = AuthState.AUTHENTICATED, isLoading = false) }
                    } else {
                        setState { copy(authState = AuthState.UNAUTHENTICATED, isLoading = false) }
                    }
                }

        }
    }

    override fun setInitialState(): AuthContract.State = AuthContract.State(
        authState = AuthState.UNAUTHENTICATED,
        isLoading = false
    )

    override fun handleEvents(event: AuthContract.Event) = Unit

}