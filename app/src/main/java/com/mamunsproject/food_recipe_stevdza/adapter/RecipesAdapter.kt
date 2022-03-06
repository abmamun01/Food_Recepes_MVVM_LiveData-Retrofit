package com.mamunsproject.food_recipe_stevdza.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mamunsproject.food_recipe_stevdza.databinding.ReciepesRowBinding
import com.mamunsproject.food_recipe_stevdza.models.FoodRecipe
import com.mamunsproject.food_recipe_stevdza.models.Result_
import com.mamunsproject.food_recipe_stevdza.utils.RecipesDiffUtil

class RecipesAdapter : RecyclerView.Adapter<RecipesAdapter.RecipesHolder>() {

     var recipesList = emptyList<Result_>()

    class RecipesHolder(private val binding: ReciepesRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(result: Result_) {
            binding.result = result
            //this function will update when user change our data
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): RecipesHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ReciepesRowBinding.inflate(layoutInflater, parent, false)
                return RecipesHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesHolder {
        return RecipesHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecipesHolder, position: Int) {
        val currentRecipe = recipesList[position]
        holder.bind(currentRecipe)
    }

    override fun getItemCount(): Int {
        return recipesList.size
    }

    fun setData(newData: FoodRecipe) {

        val recipesDiffUtil = RecipesDiffUtil(recipesList, newData.results)
        val diffUtilResult = DiffUtil.calculateDiff(recipesDiffUtil)
        recipesList = newData.results
        diffUtilResult.dispatchUpdatesTo(this)

    }
}