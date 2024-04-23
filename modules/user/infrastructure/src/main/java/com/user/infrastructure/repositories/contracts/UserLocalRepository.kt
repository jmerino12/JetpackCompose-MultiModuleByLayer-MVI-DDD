package com.user.infrastructure.repositories.contracts

import com.user.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

interface UserLocalRepository : UserRepository {
    fun isFirstTime(): Flow<Boolean>
}
