package com.auth.ui.screens.register

import androidx.lifecycle.viewModelScope
import com.auth.domain.model.Register
import com.auth.domain.repositories.AuthRepository
import com.auth.ui.screens.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor(private val authRepository: AuthRepository) :
    BaseViewModel<RegisterContract.Event, RegisterContract.State, RegisterContract.Effect>() {
    override fun setInitialState() = RegisterContract.State(
        isLoading = false,
        isError = false,
        user = null
    )

    override fun handleEvents(event: RegisterContract.Event) {
        when (event) {
            RegisterContract.Event.BackButtonClicked -> {
                setEffect { RegisterContract.Effect.Navigation.Back }
            }

            is RegisterContract.Event.Register -> {
                registerUser(event.register)
            }
        }
    }

    private fun registerUser(register: Register) {
        viewModelScope.launch(Dispatchers.IO) {
            setState { copy(isLoading = true, isError = false) }
            try {
                authRepository.registerWithEmailAndPassword(register)
            } catch (e: Exception) {
                setState { copy(isLoading = false, isError = true) }
            }
        }
    }
}