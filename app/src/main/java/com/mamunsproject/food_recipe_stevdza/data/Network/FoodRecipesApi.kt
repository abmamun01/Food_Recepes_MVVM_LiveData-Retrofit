package com.mamunsproject.food_recipe_stevdza.data.Network

import com.mamunsproject.food_recipe_stevdza.models.FoodRecipe
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface FoodRecipesApi {

    @GET("/recipes/complexSearch")
    suspend fun getRecipes(
        @QueryMap queryMap: Map<String, String>
    ): Response<FoodRecipe>


    @GET("/recipes/complexSearch")
    suspend fun searchRecipes(
        @QueryMap searQueryMap: Map<String, String>
    ): Response<FoodRecipe>
}