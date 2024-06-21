package com.example.foodapp.data.network

import com.example.foodapp.model.AreaResponse
import com.example.foodapp.model.RecipeDetailResponse
import com.example.foodapp.model.RecipeResponse
import com.example.foodapp.util.DataConstants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object RetrofitClient {
    val apiService: RecipeApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RecipeApiService::class.java)
    }
}

interface RecipeApiService {
    @GET("list.php?a=list")
    suspend fun getAreas(): AreaResponse

    @GET("filter.php")
    suspend fun getRecipesByArea(@Query("a") area: String): RecipeResponse

    @GET("lookup.php")
    suspend fun getRecipeDetails(@Query("i") id: Int): RecipeDetailResponse
}