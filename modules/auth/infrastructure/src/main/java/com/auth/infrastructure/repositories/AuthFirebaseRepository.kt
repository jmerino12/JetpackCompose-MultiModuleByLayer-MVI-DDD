package com.auth.infrastructure.repositories

import android.util.Log
import com.auth.domain.model.Login
import com.auth.domain.model.Register
import com.auth.domain.model.User
import com.auth.domain.repositories.AuthRepository
import com.auth.infrastructure.anticorruption.UserTranslate
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.userProfileChangeRequest
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class AuthFirebaseRepository @Inject constructor(
    private val auth: FirebaseAuth
) : AuthRepository {


    override val authState: Flow<User?> = callbackFlow {
        val listener = FirebaseAuth.AuthStateListener { auth ->
            val user = auth.currentUser?.let { UserTranslate.fromDtoToDomain(it) }
            trySend(user)
        }
        auth.addAuthStateListener(listener)
        awaitClose {
            auth.removeAuthStateListener(listener)
        }
    }


    override suspend fun logInWithEmailAndPassword(login: Login) {
        singIn(login)
    }

    override suspend fun registerWithEmailAndPassword(register: Register) {
        try {
            val result =
                auth.createUserWithEmailAndPassword(register.email, register.password).await()
            val token = result.user?.getIdToken(true)?.await()
            Log.v("AuthFirebaseRepository - Register", token?.token.toString())
            singIn(Login(register.email, register.password))
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

    private suspend fun singIn(login: Login) {
        try {
            val result = auth.signInWithEmailAndPassword(login.email, login.password).await()
            val token = result.user?.getIdToken(true)?.await()
            Log.v("Login", token?.token.toString())
        } catch (e: Exception) {
            Log.e("error", e.message.toString())
            throw e
        }
    }

}