package com.example.foodapp.presentation.navigation

sealed class Screen(val route: String) {
    object Welcome : Screen("welcome_screen")
    object Home : Screen("home_screen")
    object Details : Screen("details_screen/{recipeId}") {
        fun createRoute(recipeId: Int) = "details_screen/$recipeId"
    }
    object Favorites : Screen("favorites_screen")
    object Recommended : Screen("recommended_screen")
}