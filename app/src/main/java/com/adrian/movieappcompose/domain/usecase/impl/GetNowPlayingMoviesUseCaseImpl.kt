package com.adrian.movieappcompose.domain.usecase.impl

import com.adrian.movieappcompose.data.repository.MovieRepository
import com.adrian.movieappcompose.domain.model.NowPlayingMovies
import com.adrian.movieappcompose.domain.usecase.GetNowPlayingMoviesUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNowPlayingMoviesUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository
): GetNowPlayingMoviesUseCase {

    override operator fun invoke(): Flow<Result<List<NowPlayingMovies>>> {
        return movieRepository.getNowPlayingMovies()
    }
}
