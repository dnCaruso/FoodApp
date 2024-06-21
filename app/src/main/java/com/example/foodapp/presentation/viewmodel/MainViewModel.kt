package com.example.foodapp.presentation.viewmodel

import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodapp.model.Area
import com.example.foodapp.model.Recipe
import com.example.foodapp.model.RecipeDetail
import com.example.foodapp.data.network.RetrofitClient
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val apiService = RetrofitClient.apiService
    private val _recipesByArea = mutableStateMapOf<String, List<Recipe>>()
    val recipesByArea: Map<String, List<Recipe>> get() = _recipesByArea

    val areas = mutableStateOf<List<Area>>(emptyList())
    val recipes = mutableStateOf<List<Recipe>>(emptyList())
    val recipeDetails = mutableStateOf<RecipeDetail?>(null)

    init {
        fetchAreas()
    }

    fun fetchAreas() {
        viewModelScope.launch {
            try {
                val response = apiService.getAreas()
                areas.value = response.meals.take(10)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun fetchRecipesByArea(area: String) {
        if (_recipesByArea.containsKey(area).not()) {
            try {
                viewModelScope.launch {
                    val response = apiService.getRecipesByArea(area)
                    _recipesByArea[area] = response.meals
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun fetchMealDetails(id: Int) {
        try {
            viewModelScope.launch {
                val response = apiService.getRecipeDetails(id)
                recipeDetails.value = response.meals.firstOrNull()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}