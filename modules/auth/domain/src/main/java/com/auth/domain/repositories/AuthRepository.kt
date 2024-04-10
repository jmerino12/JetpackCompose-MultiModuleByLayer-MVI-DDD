package com.auth.domain.repositories

import com.auth.domain.model.Login
import com.auth.domain.model.Register
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun logInWithEmailAndPassword(login: Login)
    suspend fun registerWithEmailAndPassword(register: Register)
    suspend fun logout()
    fun getToken(): Flow<String>
}