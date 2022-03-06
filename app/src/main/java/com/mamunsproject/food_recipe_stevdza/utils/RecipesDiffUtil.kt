package com.mamunsproject.food_recipe_stevdza.utils

import androidx.recyclerview.widget.DiffUtil
import com.mamunsproject.food_recipe_stevdza.models.Result_

class RecipesDiffUtil(

    private val oldList: List<Result_>,
    private val newList: List<Result_>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {

        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]

    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

}