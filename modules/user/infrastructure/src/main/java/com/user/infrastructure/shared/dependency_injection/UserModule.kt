package com.user.infrastructure.shared.dependency_injection

import android.content.Context
import com.core.database.shared.dataStore
import com.user.domain.respository.UserRepository
import com.user.infrastructure.repositories.UserDataStoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UserModule {

    @Provides
    fun provideLocalInteractionDataSourceImpl(@ApplicationContext context: Context): UserRepository =
        UserDataStoreRepository(context.dataStore)
}