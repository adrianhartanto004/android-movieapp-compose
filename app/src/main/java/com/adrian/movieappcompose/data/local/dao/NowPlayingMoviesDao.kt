package com.adrian.movieappcompose.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.adrian.movieappcompose.data.local.entity.NowPlayingMoviesEntity

@Dao
interface NowPlayingMoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<NowPlayingMoviesEntity>)

    @Query("SELECT * FROM nowPlayingMovies LIMIT :limit")
    suspend fun fetchWithLimit(limit: Int): List<NowPlayingMoviesEntity>

    @Query("SELECT * FROM nowPlayingMovies")
    suspend fun fetchAll(): List<NowPlayingMoviesEntity>

    @Query("DELETE FROM nowPlayingMovies")
    suspend fun deleteAll()
}
