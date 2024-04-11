package com.example.infrastructure.shared.dependency_injection

import com.example.domain.repositories.MovieRepository
import com.example.infrastructure.NetworkVerify
import com.example.infrastructure.dao.MovieDao
import com.example.infrastructure.httpclient.service.MovieService
import com.example.infrastructure.repository.MovieProxy
import com.example.infrastructure.repository.MoviesRetrofitRepository
import com.example.infrastructure.repository.MoviesRoomRepository
import com.example.infrastructure.repository.contracts.MovieLocalRepository
import com.example.infrastructure.repository.contracts.MovieRemoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieInfrastructureModule {

    @Provides
    @Singleton
    fun providesMovieServiceApi(retrofit: Retrofit): MovieService {
        return retrofit.create(MovieService::class.java)
    }

    @Provides
    @Singleton
    fun providesMovieRemoteDatasource(movieService: MovieService): MovieRemoteRepository =
        MoviesRetrofitRepository(movieService)

    @Provides
    @Singleton
    fun providesMovieLocalDatasource(movieDao: MovieDao): MovieLocalRepository =
        MoviesRoomRepository(movieDao)

    @Provides
    @Singleton
    fun providesMovieRepository(
        localRepository: MovieLocalRepository,
        remoteRepository: MovieRemoteRepository
    ): MovieRepository = MovieProxy(localRepository, remoteRepository, NetworkVerify())
}