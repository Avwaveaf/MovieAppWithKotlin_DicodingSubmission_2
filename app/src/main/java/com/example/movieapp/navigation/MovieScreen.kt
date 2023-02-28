package com.example.movieapp.navigation

enum class MovieScreen {
    HomeScreen,
    MovieDetailScreen,
    ProfileScreen,
    SplashScreen;

    companion object {
        fun fromRoute(route: String?): MovieScreen = when (route?.substringBefore("/")) {
            HomeScreen.name -> HomeScreen
            MovieDetailScreen.name -> MovieDetailScreen
            ProfileScreen.name -> ProfileScreen
            SplashScreen.name -> SplashScreen
            null -> HomeScreen
            else -> throw java.lang.IllegalArgumentException("404 Not found")
        }

    }
}
