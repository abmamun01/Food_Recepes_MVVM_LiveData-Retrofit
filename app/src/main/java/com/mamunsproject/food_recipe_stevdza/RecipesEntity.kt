package com.mamunsproject.food_recipe_stevdza

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mamunsproject.food_recipe_stevdza.models.FoodRecipe
import com.mamunsproject.food_recipe_stevdza.utils.Constant.Companion.RECIPES_TABLE

@Entity(tableName = RECIPES_TABLE)
class RecipesEntity(
    var foodRecipe: FoodRecipe
) {

    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}