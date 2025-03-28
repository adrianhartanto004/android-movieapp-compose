package com.adrian.movieappcompose.domain.usecase.impl

import com.adrian.movieappcompose.data.repository.MovieRepository
import com.adrian.movieappcompose.domain.usecase.FetchNowPlayingMoviesUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchNowPlayingMoviesUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository
): FetchNowPlayingMoviesUseCase {

    override operator fun invoke(): Flow<Result<Unit>> {
        return movieRepository.fetchNowPlayingMovies()
    }
}
