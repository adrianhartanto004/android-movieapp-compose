package com.adrian.movieappcompose.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.adrian.movieappcompose.data.local.dao.NowPlayingMoviesDao
import com.adrian.movieappcompose.data.local.entity.NowPlayingMoviesEntity

@Database(
    entities = [NowPlayingMoviesEntity::class],
    version = 1,
    exportSchema = false
)

abstract class MovieAppDatabase: RoomDatabase() {

    abstract val nowPlayingMoviesDao: NowPlayingMoviesDao

    companion object {
        const val DATABASE_NAME = "movie_app_db"
    }
}
