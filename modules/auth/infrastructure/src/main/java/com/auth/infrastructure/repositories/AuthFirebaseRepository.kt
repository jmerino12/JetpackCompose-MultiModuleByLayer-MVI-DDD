package com.auth.infrastructure.repositories

import android.util.Log
import com.auth.domain.model.Login
import com.auth.domain.model.Register
import com.auth.domain.repositories.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class AuthFirebaseRepository @Inject constructor(
    private val auth: FirebaseAuth
) : AuthRepository {
    override suspend fun logInWithEmailAndPassword(login: Login) {
        try {
            val result = auth.signInWithEmailAndPassword(login.email, login.password).await()
            val token = result.user?.getIdToken(true)?.await()
            Log.v("Login", token?.token.toString())
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun registerWithEmailAndPassword(register: Register) {
        try {
            val result =
                auth.createUserWithEmailAndPassword(register.email, register.password).await()
            val token = result.user?.getIdToken(true)?.await()
            Log.v("AuthFirebaseRepository - Register", token?.token.toString())
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun logout() {
        auth.signOut()
    }

    override fun getToken(): Flow<String> {
        return flow {
            val token = auth.currentUser?.getIdToken(true)?.await()?.token
            emit(token ?: "")
        }
    }
}