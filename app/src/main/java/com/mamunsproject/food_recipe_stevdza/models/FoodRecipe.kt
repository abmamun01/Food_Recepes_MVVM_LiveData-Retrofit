package com.mamunsproject.food_recipe_stevdza.models


import com.google.gson.annotations.SerializedName

data class FoodRecipe(

    @SerializedName("results")
    val results: List<Result_>,


    )