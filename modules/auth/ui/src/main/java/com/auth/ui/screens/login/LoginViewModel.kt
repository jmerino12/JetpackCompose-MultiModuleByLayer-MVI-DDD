package com.auth.ui.screens.login

import androidx.lifecycle.viewModelScope
import com.auth.domain.model.Login
import com.auth.domain.repositories.AuthRepository
import com.auth.ui.screens.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authRepository: AuthRepository) :
    BaseViewModel<LoginContract.Event, LoginContract.State, LoginContract.Effect>() {

    override fun setInitialState() = LoginContract.State(
        isLoading = false,
        isError = false,
    )

    override fun handleEvents(event: LoginContract.Event) {
        when (event) {
            is LoginContract.Event.Login -> {
                login(event.login)
            }
            is LoginContract.Event.Register -> setEffect { LoginContract.Effect.Navigation.ToRegister }
        }
    }

    private fun login(login: Login) {
        viewModelScope.launch(Dispatchers.IO) {
            setState { copy(isLoading = true, isError = false) }
            try {
                authRepository.logInWithEmailAndPassword(login)
                setState { copy(isLoading = false) }
            } catch (e: Exception) {
                setState { copy(isLoading = false, isError = true) }
            }
        }
    }
}