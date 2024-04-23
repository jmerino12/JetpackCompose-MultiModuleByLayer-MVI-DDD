package com.user.domain.repository

import com.user.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUser(): Flow<User>
    suspend fun saveUser(user: User)
}