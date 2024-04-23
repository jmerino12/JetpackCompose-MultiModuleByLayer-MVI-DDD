package com.user.infrastructure.repositories

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.userProfileChangeRequest
import com.user.domain.model.User
import com.user.infrastructure.anticorruption.UserTranslate
import com.user.infrastructure.repositories.contracts.UserRemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserFirebaseRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : UserRemoteRepository {
    override fun getUser(): Flow<User> {
        return flow {
            val user = firebaseAuth.currentUser?.let { UserTranslate.fromDtoToDomain(it) }
            emit(user!!)
        }

    }

    override suspend fun saveUser(user: User) {
        updateInformation(user)
    }

    private suspend fun updateInformation(user: User): Boolean {
        return try {
            val profileUpdates = userProfileChangeRequest {
                displayName = user.name
            }
            firebaseAuth.currentUser!!.updateProfile(profileUpdates).await()
            true
        } catch (e: Exception) {
            false
        }
    }
}