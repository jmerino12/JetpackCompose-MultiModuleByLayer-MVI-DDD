package com.core.database.shared

import android.app.Application
import androidx.room.Room
import com.core.database.movie.dao.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun databaseProvider(app: Application): MovieShopDatabase =
        Room.databaseBuilder(app, MovieShopDatabase::class.java, "movie-shop-db")
            .build()


    @Provides
    fun providesMovieDao(database: MovieShopDatabase): MovieDao = database.movieDao()
}