package com.example.foodapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.foodapp.presentation.view.DetailsScreen
import com.example.foodapp.presentation.view.FavoritesScreen
import com.example.foodapp.presentation.view.HomeScreen
import com.example.foodapp.presentation.view.RecommendedScreen
import com.example.foodapp.presentation.view.WelcomeScreen
import com.example.foodapp.presentation.viewmodel.MainViewModel

@Composable
fun FoodAppNavGraph(navController: NavHostController, viewModel: MainViewModel = viewModel()) {

    NavHost(
        navController = navController,
        startDestination = Screen.Welcome.route
    ) {
        composable(Screen.Welcome.route) {
            WelcomeScreen(navController = navController)
        }
        composable(Screen.Home.route) {
            HomeScreen(viewModel = viewModel, navController = navController)
        }
        composable(
            route = Screen.Details.route,
            arguments = listOf(navArgument("recipeId") { type = NavType.IntType })
        ) { backStackEntry ->
            val recipeId = backStackEntry.arguments?.getInt("recipeId") ?: 0
            viewModel.fetchMealDetails(recipeId)
            val recipeDetails = viewModel.recipeDetails.value
            recipeDetails?.let {
                DetailsScreen(
                    recipeTitle = it.strMeal,
                    imageUrl = it.strMealThumb,
                    strInstructions = it.strInstructions,
                    ingredients = listOfNotNull(
                        it.strIngredient1,
                        it.strIngredient2,
                        it.strIngredient3,
                        it.strIngredient4,
                        it.strIngredient5,
                        it.strIngredient6,
                        it.strIngredient7,
                        it.strIngredient8,
                        it.strIngredient9,
                        it.strIngredient10,
                        it.strIngredient11,
                        it.strIngredient12,
                        it.strIngredient13,
                        it.strIngredient14,
                        it.strIngredient15,
                        it.strIngredient16,
                        it.strIngredient17,
                        it.strIngredient18,
                        it.strIngredient19,
                        it.strIngredient20
                    ),
                    measures = listOfNotNull(
                        it.strMeasure1,
                        it.strMeasure2,
                        it.strMeasure3,
                        it.strMeasure4,
                        it.strMeasure5,
                        it.strMeasure6,
                        it.strMeasure7,
                        it.strMeasure8,
                        it.strMeasure9,
                        it.strMeasure10,
                        it.strMeasure11,
                        it.strMeasure12,
                        it.strMeasure13,
                        it.strMeasure14,
                        it.strMeasure15,
                        it.strMeasure16,
                        it.strMeasure17,
                        it.strMeasure18,
                        it.strMeasure19,
                        it.strMeasure20
                    ),
                    navController = navController
                )
            }
        }
            composable(Screen.Favorites.route) {
                FavoritesScreen()
            }
            composable(Screen.Recommended.route) {
                RecommendedScreen()
            }
        }
    }