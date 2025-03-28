package com.adrian.movieappcompose.di

import com.adrian.movieappcompose.data.repository.MovieRepository
import com.adrian.movieappcompose.domain.usecase.FetchNowPlayingMoviesUseCase
import com.adrian.movieappcompose.domain.usecase.GetNowPlayingMoviesUseCase
import com.adrian.movieappcompose.domain.usecase.impl.FetchNowPlayingMoviesUseCaseImpl
import com.adrian.movieappcompose.domain.usecase.impl.GetNowPlayingMoviesUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Provides
    @Singleton
    fun provideFetchNowPlayingMoviesUseCaseImpl(movieRepository: MovieRepository): FetchNowPlayingMoviesUseCase {
        return FetchNowPlayingMoviesUseCaseImpl(movieRepository)
    }

    @Provides
    @Singleton
    fun provideGetNowPlayingMoviesUseCaseImpl(movieRepository: MovieRepository): GetNowPlayingMoviesUseCase {
        return GetNowPlayingMoviesUseCaseImpl(movieRepository)
    }
}
