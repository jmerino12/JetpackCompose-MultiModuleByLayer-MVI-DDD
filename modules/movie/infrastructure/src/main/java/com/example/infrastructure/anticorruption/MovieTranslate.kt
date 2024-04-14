package com.example.infrastructure.anticorruption

import com.core.database.movie.entity.MovieEntity
import com.example.domain.model.Movie
import com.example.infrastructure.httpclient.dto.MovieDto

class MovieTranslate {
    companion object {
        fun fromMovieEntityToDomain(movieEntity: MovieEntity): Movie {
            return Movie(
                movieEntity.id,
                movieEntity.originalLanguage,
                movieEntity.overview,
                movieEntity.posterPath,
                movieEntity.backdropPath,
                movieEntity.releaseDate,
                movieEntity.title,
                movieEntity.voteAverage,
                movieEntity.isAdult
            )
        }

        fun fromDomainToMovieEntity(movie: Movie): MovieEntity {
            return MovieEntity(
                movie.id,
                movie.originalLanguage,
                movie.overview,
                movie.posterPath,
                movie.backdropPath,
                movie.releaseDate,
                movie.title,
                movie.voteAverage,
                movie.isAdult
            )
        }

        fun fromDtoToDomain(movieDto: MovieDto): Movie {
            return Movie(
                movieDto.id,
                movieDto.originalLanguage,
                movieDto.overview,
                movieDto.posterPath,
                movieDto.backdropPath,
                movieDto.releaseDate,
                movieDto.title,
                movieDto.voteAverage,
                movieDto.adult
            )
        }
    }
}