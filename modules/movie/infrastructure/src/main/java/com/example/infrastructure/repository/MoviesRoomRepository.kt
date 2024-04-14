package com.example.infrastructure.repository

import com.core.database.movie.dao.MovieDao
import com.example.domain.model.Movie
import com.example.infrastructure.anticorruption.MovieTranslate
import com.example.infrastructure.repository.contracts.MovieLocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MoviesRoomRepository @Inject constructor(private val movieDao: MovieDao) :
    MovieLocalRepository {
    override fun isEmpty(): Flow<Boolean> = movieDao.getCountMovies().map { count -> count <= 0 }

    override suspend fun insertMovies(movies: List<Movie>) {
        movieDao.insertMovies(movies.map { MovieTranslate.fromDomainToMovieEntity(it) })
    }

    override fun getMovies(): Flow<List<Movie>> {
        return movieDao.getAllMovies()
            .map { movies -> movies.map { MovieTranslate.fromMovieEntityToDomain(it) } }
    }

    override fun getMovie(id: Int): Flow<Movie?> {
        return movieDao.getMovie(id)
            .map { movie -> movie?.let { MovieTranslate.fromMovieEntityToDomain(it) } }
    }
}