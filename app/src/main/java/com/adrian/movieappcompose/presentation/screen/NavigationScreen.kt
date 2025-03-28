package com.adrian.movieappcompose.presentation.screen

sealed class NavigationScreen(val route: String) {
    data object Home : NavigationScreen("home")
    data object MovieDetail : NavigationScreen("detail/{movieId}") {
        fun passId(id: Int) = "detail/$id"
    }
    data object FavouriteMovies : NavigationScreen("favourite")
}
