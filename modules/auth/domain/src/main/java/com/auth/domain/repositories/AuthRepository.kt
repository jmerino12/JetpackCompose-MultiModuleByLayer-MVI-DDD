package com.auth.domain.repositories

import com.auth.domain.model.Login
import com.auth.domain.model.Register
import com.auth.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface AuthRepository {
    val authState: Flow<User?>
    suspend fun logInWithEmailAndPassword(login: Login)
    suspend fun registerWithEmailAndPassword(register: Register)
    suspend fun logout()
    fun getToken(): Flow<String>
}