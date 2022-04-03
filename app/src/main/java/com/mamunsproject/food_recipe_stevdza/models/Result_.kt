package com.mamunsproject.food_recipe_stevdza.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

//Parcel ize will help add this in navigation graph to parse something
@Parcelize
data class Result_(

    @SerializedName("aggregateLikes")
    val aggregateLikes: Int,

    @SerializedName("cheap")
    val cheap: Boolean,

    @SerializedName("dairyFree")
    val dairyFree: Boolean,

    @SerializedName("veryHealthy")
    val veryHealthy: Boolean,

    @SerializedName("extendedIngredients")
    val extendedIngredients: List<ExtendedIngredient>,

    @SerializedName("glutenFree")
    val glutenFree: Boolean,

    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,

    @SerializedName("readyInMinutes")
    val readyInMinutes: Int,

    @SerializedName("sourceName")
    val sourceName: String?,

    @SerializedName("sourceUrl")
    val sourceUrl: String,

    @SerializedName("summary")
    val summary: String,

    @SerializedName("sustainable")
    val sustainable: Boolean,

    @SerializedName("title")
    val title: String,

    @SerializedName("vegan")
    val vegan: Boolean,


    @SerializedName("vegetarian")
    val vegetarian: Boolean,
    @SerializedName("weightWatcherSmartPoints")
    val weightWatcherSmartPoints: Int

):Parcelable