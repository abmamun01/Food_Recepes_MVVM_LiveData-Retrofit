package com.mamunsproject.food_recipe_stevdza.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mamunsproject.food_recipe_stevdza.models.FoodRecipe

class RecipesTypeConverter {

    var gson = Gson()

    //Here using gson library to convert FoodRecipe Object to String
    @TypeConverter
    fun foodRecipeToString(foodRecipe: FoodRecipe): String {
        return gson.toJson(foodRecipe)
    }


    @TypeConverter
    fun stringToFoodRecipe(data: String): FoodRecipe {
        val listType = object : TypeToken<FoodRecipe>() {}.type
        return gson.fromJson(data, listType)
    }
}