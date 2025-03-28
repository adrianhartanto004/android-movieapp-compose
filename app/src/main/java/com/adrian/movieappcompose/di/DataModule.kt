package com.adrian.movieappcompose.di

import android.app.Application
import androidx.room.Room
import com.adrian.movieappcompose.data.local.MovieAppDatabase
import com.adrian.movieappcompose.data.local.dao.NowPlayingMoviesDao
import com.adrian.movieappcompose.data.remote.service.MovieAppService
import com.adrian.movieappcompose.data.repository.MovieRepository
import com.adrian.movieappcompose.data.repository.impl.MovieRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideMovieAppDatabase(application: Application): MovieAppDatabase {
        return Room.databaseBuilder(
            application,
            MovieAppDatabase::class.java,
            MovieAppDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideMovieAppService(
        retrofit: Retrofit,
    ): MovieAppService =
        retrofit.create(MovieAppService::class.java)

    @Provides
    internal fun provideDao(movieAppDatabase: MovieAppDatabase): NowPlayingMoviesDao {
        return movieAppDatabase.nowPlayingMoviesDao
    }

    @Provides
    @Singleton
    fun provideMovieRepository(
        movieAppService: MovieAppService,
        nowPlayingMoviesDao: NowPlayingMoviesDao
    ): MovieRepository {
        return MovieRepositoryImpl(movieAppService, nowPlayingMoviesDao)
    }
}
