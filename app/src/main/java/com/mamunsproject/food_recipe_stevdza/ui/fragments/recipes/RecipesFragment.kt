package com.mamunsproject.food_recipe_stevdza.ui.fragments.recipes

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mamunsproject.food_recipe_stevdza.viewmodels.MainViewModel
import com.mamunsproject.food_recipe_stevdza.R
import com.mamunsproject.food_recipe_stevdza.adapter.RecipesAdapter
import com.mamunsproject.food_recipe_stevdza.databinding.FragmentRecipesBinding
import com.mamunsproject.food_recipe_stevdza.observeOnce
import com.mamunsproject.food_recipe_stevdza.utils.Constant.Companion.API_KEY
import com.mamunsproject.food_recipe_stevdza.utils.NetworkResult
import com.mamunsproject.food_recipe_stevdza.viewmodels.RecipesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_recipes.view.*
import kotlinx.coroutines.launch


@AndroidEntryPoint
class RecipesFragment : Fragment() {

    private var _binding: FragmentRecipesBinding? = null
    private val binding get() = _binding!!


    private lateinit var mainViewModel: MainViewModel
    private lateinit var recipesViewModel: RecipesViewModel

    private val mAdapter by lazy { RecipesAdapter() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        recipesViewModel = ViewModelProvider(requireActivity())[RecipesViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRecipesBinding.inflate(inflater, container, false)
        binding.mainViewModel = mainViewModel


        binding.lifecycleOwner = this
        setupRecyclerView()

        //This Function will read the database instead calling API again & again
        readDatabase()


        binding.recipesFav.setOnClickListener {
            findNavController().navigate(R.id.action_recipesFragment_to_recipesBottomSheet)
        }

        return binding.root;
    }

    private fun readDatabase() {


        lifecycleScope.launch {
            /** Instead of calling Observe , we calling ObserveOnce Function which we created in MyExtension
             * Because when the application run for the first time (readDatabase & requestApiData) call both
             * to get read from this type of bug this MyExtensionFunction file created
             */
            mainViewModel.readRecipes.observeOnce(viewLifecycleOwner, { database ->

                /// If database is not then Set Data from ROOM DB else Request new API*/
                if (database.isNotEmpty()) {
                    Log.d("RecipesFragment", "readDatabaseCall")

                    mAdapter.setData(database[0].foodRecipe)
                    hideShimmerEffect()
                } else {
                    requestApiData()
                }
            })
        }

    }

    private fun loadDataFromCache() {

        lifecycleScope.launch {
            mainViewModel.readRecipes.observe(viewLifecycleOwner, { database ->
                if (database.isNotEmpty()) {
                    mAdapter.setData(database[0].foodRecipe)
                }
            })
        }
    }

    private fun requestApiData() {

        Log.d("RecipesFragment", "requestApiDataCall")
        mainViewModel.getRecipes(recipesViewModel.applyQueries())
        mainViewModel.recipesResponse.observe(viewLifecycleOwner, { response ->

            when (response) {
                is NetworkResult.Success -> {
                    hideShimmerEffect()
                    response.data?.let { mAdapter.setData(it) }
                }
                is NetworkResult.Error -> {
                    hideShimmerEffect()
                    /** If Server has Error then Load call from Room DB old Data*/
                    loadDataFromCache()


                    Toast.makeText(
                        requireContext(),
                        response.message.toString() + "HELLLO ERROR",
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
        binding.recyclerview.adapter = mAdapter
        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())
        showShimmerEffect()
    }

    private fun showShimmerEffect() {
        binding.recyclerview.showShimmer()
    }


    private fun hideShimmerEffect() {
        binding.recyclerview.hideShimmer()
    }

    override fun onDestroy() {
        super.onDestroy()

        /** By this we can safe from Memory Leaks*/
        _binding = null
    }

}