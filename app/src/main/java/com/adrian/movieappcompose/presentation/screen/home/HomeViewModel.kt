package com.adrian.movieappcompose.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adrian.movieappcompose.domain.model.NowPlayingMovies
import com.adrian.movieappcompose.domain.usecase.FetchNowPlayingMoviesUseCase
import com.adrian.movieappcompose.domain.usecase.GetNowPlayingMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchNowPlayingMoviesUseCase: FetchNowPlayingMoviesUseCase,
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HomeScreenState())
    val state: StateFlow<HomeScreenState> = _state

    fun onEvent(event: HomeScreenEvent) {
        when (event) {
            is HomeScreenEvent.LoadNowPlayingMovies -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _state.update { it.copy(isLoading = true) }
                    getNowPlayingMoviesUseCase().collectLatest { result ->
                        if (result.isSuccess) {
                            _state.update {
                                it.copy(
                                    nowPlayingMovies = result.getOrDefault(emptyList()),
                                    isLoading = false
                                )
                            }
                        } else {
                            _state.update {
                                it.copy(
                                    nowPlayingMovies = emptyList(),
                                    isLoading = false
                                )
                            }
                        }
                    }
                }
            }

            is HomeScreenEvent.RefreshNowPlayingMovies -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _state.update { it.copy(isRefreshing = true) }
                    fetchNowPlayingMoviesUseCase()
                        .collectLatest { result ->
                            if (result.isSuccess) {
                                getNowPlayingMoviesUseCase()
                                    .collectLatest { dbResult ->
                                        if (dbResult.isSuccess) {
                                            _state.update {
                                                it.copy(
                                                    nowPlayingMovies = dbResult.getOrDefault(
                                                        emptyList()
                                                    ),
                                                    isRefreshing = false
                                                )
                                            }
                                        } else {
                                            _state.update {
                                                it.copy(
                                                    nowPlayingMovies = emptyList(),
                                                    isRefreshing = false
                                                )
                                            }
                                        }
                                    }
                            }
                        }
                    getNowPlayingMoviesUseCase()
                }
            }
        }
    }
}

data class HomeScreenState(
    val nowPlayingMovies: List<NowPlayingMovies> = emptyList(),
    val isRefreshing: Boolean = false,
    val isLoading: Boolean = false
)

sealed class HomeScreenEvent {
    data object RefreshNowPlayingMovies : HomeScreenEvent()
    data object LoadNowPlayingMovies : HomeScreenEvent()
//    data class LoadMoreNowPlayingMovies(val nowPlayingMovies: List<NowPlayingMovies>): HomeScreenEvent()
}
