package com.adrian.movieappcompose.presentation.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.adrian.movieappcompose.presentation.screen.detail.MovieDetailScreen
import com.adrian.movieappcompose.presentation.screen.favourite.FavouriteMoviesScreen
import com.adrian.movieappcompose.presentation.screen.home.HomeScreen
import com.adrian.movieappcompose.presentation.screen.home.HomeViewModel

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val currentDestination by navController.currentBackStackEntryAsState()
    val currentRoute = currentDestination?.destination?.route
    val selectedTab = BottomNavigationMenu.entries.find { it.route == currentRoute }

    Scaffold(
        bottomBar = {
            selectedTab?.let {
                BottomNavigationBar(
                    selectedTab = it,
                    onTabSelected = { tab ->
                        val targetRoute = when (tab) {
                            BottomNavigationMenu.Home -> NavigationScreen.Home.route
                            BottomNavigationMenu.Favourites -> NavigationScreen.FavouriteMovies.route
                        }
                        navController.navigate(targetRoute) {
                            // Avoid multiple copies of the same destination
                            launchSingleTop = true
                            restoreState = true
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                        }
                    }
                )
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = NavigationScreen.Home.route,
            modifier = Modifier.padding(padding)
        ) {
            composable(NavigationScreen.Home.route) {
                val viewModel: HomeViewModel = hiltViewModel()
                HomeScreen(viewModel) { movieId ->
                    navController.navigate(NavigationScreen.MovieDetail.passId(movieId))
                }
            }

            composable(NavigationScreen.FavouriteMovies.route) {
                FavouriteMoviesScreen()
            }

            composable(
                route = NavigationScreen.MovieDetail.route,
                arguments = listOf(navArgument("movieId") { type = NavType.IntType })
            ) { backStackEntry ->
                val movieId = backStackEntry.arguments?.getInt("movieId") ?: -1
                MovieDetailScreen(movieId)
            }
        }
    }
}
