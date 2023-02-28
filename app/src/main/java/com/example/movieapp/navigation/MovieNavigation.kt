package com.example.movieapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieapp.screens.details.DetailScreen
import com.example.movieapp.screens.home.HomeScreen
import com.example.movieapp.screens.profile.ProfileScreen
import com.example.movieapp.screens.splashScreen.SplashScreens

@Composable
fun MovieNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = MovieScreen.SplashScreen.name) {
        composable(MovieScreen.HomeScreen.name) {
            HomeScreen(navController = navController)
        }

        composable(
            MovieScreen.MovieDetailScreen.name + "/{movie}",
            arguments = listOf(navArgument(name = "movie") { type = NavType.StringType })
        ) { backStackEntry ->
            DetailScreen(
                navController = navController,
                backStackEntry.arguments?.getString("movie")
            )
        }

        composable(MovieScreen.ProfileScreen.name) {
            ProfileScreen(navController = navController)
        }

        composable(MovieScreen.SplashScreen.name){
            SplashScreens(navController=navController)
        }
    }
}