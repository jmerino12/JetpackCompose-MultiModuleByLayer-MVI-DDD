package com.user.infrastructure.repositories

import com.user.domain.model.User
import com.user.domain.repository.UserRepository
import com.user.infrastructure.repositories.contracts.UserLocalRepository
import com.user.infrastructure.repositories.contracts.UserRemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class UserProxy @Inject constructor(
    private val remoteRepository: UserRemoteRepository,
    private val localRepository: UserLocalRepository
) : UserRepository {
    override fun getUser(): Flow<User> {
        return combine(
            remoteRepository.getUser(),
            localRepository.getUser()
        ) { remoteUser, localUser ->
            User(remoteUser.name, remoteUser.email, localUser.birthday)
        }
    }

    override suspend fun saveUser(user: User) {
        remoteRepository.saveUser(user)
        localRepository.saveUser(user)
    }
}