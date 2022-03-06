package com.mamunsproject.food_recipe_stevdza.ui.fragments.recipes

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mamunsproject.food_recipe_stevdza.viewmodels.MainViewModel
import com.mamunsproject.food_recipe_stevdza.R
import com.mamunsproject.food_recipe_stevdza.adapter.RecipesAdapter
import com.mamunsproject.food_recipe_stevdza.utils.Constant.Companion.API_KEY
import com.mamunsproject.food_recipe_stevdza.utils.NetworkResult
import com.mamunsproject.food_recipe_stevdza.viewmodels.RecipesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_recipes.view.*


@AndroidEntryPoint
class RecipesFragment : Fragment() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var recipesViewModel: RecipesViewModel

    private val mAdapter by lazy { RecipesAdapter() }
    private lateinit var mView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        recipesViewModel= ViewModelProvider(requireActivity())[RecipesViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_recipes, container, false)

        setupRecyclerView()

        requestApiData()

        return mView;
    }

    private fun requestApiData() {

        mainViewModel.getRecipes(recipesViewModel.applyQueries())
        mainViewModel.recipesResponse.observe(viewLifecycleOwner, { response ->

            when (response) {
                is NetworkResult.Success -> {
                    hideShimmerEffect()
                    response.data?.let { mAdapter.setData(it) }
                }
                is NetworkResult.Error -> {
                    hideShimmerEffect()
                    Toast.makeText(
                        requireContext(),
                        response.message.toString()+"HELLLO ERROR",
                        Toast.LENGTH_SHORT
                    ).show()


                    Log.d("SDKFDK", "requestApiData:${response.data.toString()}")


                }

                is NetworkResult.Loading -> {
                    showShimmerEffect()
                }
            }
        })

    }




    private fun setupRecyclerView() {
        mView.recyclerview.adapter = mAdapter
        mView.recyclerview.layoutManager = LinearLayoutManager(requireContext())
        showShimmerEffect()
    }

    private fun showShimmerEffect() {
        mView.recyclerview.showShimmer()
    }


    private fun hideShimmerEffect() {
        mView.recyclerview.hideShimmer()
    }

}