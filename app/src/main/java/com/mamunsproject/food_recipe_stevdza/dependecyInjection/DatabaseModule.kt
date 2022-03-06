package com.mamunsproject.food_recipe_stevdza.dependecyInjection

import android.content.Context
import androidx.room.Room
import com.mamunsproject.food_recipe_stevdza.database.RecipesDatabase
import com.mamunsproject.food_recipe_stevdza.utils.Constant.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {


    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        RecipesDatabase::class.java,
        DATABASE_NAME
    ).build()


    @Singleton
    @Provides
    fun provideDao(database: RecipesDatabase) = database.recipesDao()

}