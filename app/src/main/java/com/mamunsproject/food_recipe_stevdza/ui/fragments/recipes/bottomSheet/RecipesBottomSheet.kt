package com.mamunsproject.food_recipe_stevdza.ui.fragments.recipes.bottomSheet

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.mamunsproject.food_recipe_stevdza.R
import com.mamunsproject.food_recipe_stevdza.utils.Constant.Companion.DEFAULT_DIET_TYPE
import com.mamunsproject.food_recipe_stevdza.utils.Constant.Companion.DEFAULT_MEAL_TYPE
import com.mamunsproject.food_recipe_stevdza.viewmodels.RecipesViewModel
import kotlinx.android.synthetic.main.fragment_recipes_bottom_sheet.*
import kotlinx.android.synthetic.main.fragment_recipes_bottom_sheet.view.*
import java.lang.Exception
import java.util.*


class RecipesBottomSheet : BottomSheetDialogFragment() {

    private lateinit var recipesViewModel: RecipesViewModel
    private var mealTypeChip = DEFAULT_MEAL_TYPE
    private var dietTypeChip = DEFAULT_DIET_TYPE
    private var mealTypeChipId = 0
    private var dietTypeChipId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recipesViewModel = ViewModelProvider(requireActivity())[RecipesViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val mView = inflater.inflate(R.layout.fragment_recipes_bottom_sheet, container, false)


        /** To Read saved Data from DataStore
         * if there is data in dataStore then we are going to get those
         * and apply them in our Global Variable
         * */
        // it's a flow so we are converting it into liveData
        recipesViewModel.readMealAndDietType.asLiveData().observe(viewLifecycleOwner, { value ->

            //Saving in two global variables
            mealTypeChip = value.selectedMealType
            dietTypeChip = value.selectedDietType

            updateChip(value.selectedMealTypeId, mView.mealTypeCheapGroup)
            updateChip(value.selectedDietTypeId, mView.dietCheapGroup)
        })








        mView.mealTypeCheapGroup.setOnCheckedChangeListener { group, selectedChipId ->
            val chip = group.findViewById<Chip>(selectedChipId)
            val selectedMealType = chip.text.toString().toLowerCase(Locale.ROOT)

            // Selected String will store here
            mealTypeChip = selectedMealType
            // Selected integer will store here
            mealTypeChipId = selectedChipId

        }


        mView.dietCheapGroup.setOnCheckedChangeListener { group, selectedCheckedId ->
            val chip = group.findViewById<Chip>(selectedCheckedId)
            val selectedDietType = chip.text.toString().toLowerCase(Locale.ROOT)


            // Selected String will store here
            dietTypeChip = selectedDietType
            // Selected integer will store here
            dietTypeChipId = selectedCheckedId
        }


        mView.applyButton.setOnClickListener {
            recipesViewModel.saveMealAndDietType(
                mealTypeChip,
                mealTypeChipId,
                dietTypeChip,
                dietTypeChipId
            )

            val action =
                RecipesBottomSheetDirections.actionRecipesBottomSheetToRecipesFragment(true)
            findNavController().navigate(action)
        }
        return mView;
    }

    private fun updateChip(chipId: Int, chipGroup: ChipGroup) {

        if (chipId != 0) {
            try {
                chipGroup.findViewById<Chip>(chipId).isChecked = true
            } catch (e: Exception) {

                Log.d("RecipesBottomSheetError", "updateChip: + ${e.message.toString()}")
            }
        }
    }


}