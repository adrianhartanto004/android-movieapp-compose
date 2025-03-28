package com.adrian.movieappcompose.presentation.screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

enum class BottomNavigationMenu(val label: String, val icon: ImageVector, val route: String) {
    Home("Home", Icons.Default.Home, NavigationScreen.Home.route),
    Favourites("Favourites", Icons.Default.Favorite, NavigationScreen.FavouriteMovies.route)
}
