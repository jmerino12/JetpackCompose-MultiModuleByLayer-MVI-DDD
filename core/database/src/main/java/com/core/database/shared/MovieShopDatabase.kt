package com.core.database.shared

import androidx.room.Database
import androidx.room.RoomDatabase
import com.core.database.movie.dao.MovieDao
import com.core.database.movie.entity.MovieEntity

/**
 * Created by jmerino on 13/09/23
 */
@Database(
    entities = [MovieEntity::class],
    version = 1
)
abstract class MovieShopDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}