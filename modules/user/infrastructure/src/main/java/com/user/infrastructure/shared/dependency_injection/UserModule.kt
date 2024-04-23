package com.user.infrastructure.shared.dependency_injection

import android.content.Context
import com.core.database.shared.dataStore
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.user.domain.repository.UserRepository
import com.user.infrastructure.repositories.UserDataStoreRepository
import com.user.infrastructure.repositories.UserFirebaseRepository
import com.user.infrastructure.repositories.UserProxy
import com.user.infrastructure.repositories.contracts.UserLocalRepository
import com.user.infrastructure.repositories.contracts.UserRemoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UserModule {

    @Provides
    fun provideUserLocalDatasource(@ApplicationContext context: Context): UserLocalRepository =
        UserDataStoreRepository(context.dataStore)

    @Provides
    fun provideUserRemoteDataSource(firebaseAuth: FirebaseAuth): UserRemoteRepository =
        UserFirebaseRepository(firebaseAuth)

    @Provides
    fun provideUserRepository(
        remoteRepository: UserRemoteRepository,
        localRepository: UserLocalRepository
    ): UserRepository = UserProxy(remoteRepository, localRepository)
}