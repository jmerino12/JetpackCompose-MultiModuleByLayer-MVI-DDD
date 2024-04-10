package com.example.infrastructure.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.infrastructure.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Transaction
    @Query("SELECT * FROM MOVIES")
    fun getAllMovies(): Flow<List<MovieEntity>>

    @Transaction
    @Query("SELECT * FROM MOVIES WHERE id= :id")
    fun getMovie(id: Int): Flow<MovieEntity?>

    @Transaction
    @Query("SELECT COUNT(*) FROM MOVIES")
    fun getCountMovies(): Flow<Int>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)
}