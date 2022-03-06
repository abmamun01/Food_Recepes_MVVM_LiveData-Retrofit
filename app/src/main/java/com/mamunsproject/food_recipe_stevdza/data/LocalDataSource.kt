package com.mamunsproject.food_recipe_stevdza.data

import com.mamunsproject.food_recipe_stevdza.database.RecipesDao
import com.mamunsproject.food_recipe_stevdza.database.RecipesEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val recipesDao: RecipesDao
) {

    suspend fun readDatabase(): Flow<List<RecipesEntity>> {
        return recipesDao.readRecipes()
    }

    suspend fun insertRecipes(recipesEntity: RecipesEntity) {
        recipesDao.insertRecipes(recipesEntity)
    }
}