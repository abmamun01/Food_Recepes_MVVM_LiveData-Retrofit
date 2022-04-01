package com.mamunsproject.food_recipe_stevdza.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.navArgs
import com.mamunsproject.food_recipe_stevdza.R
import com.mamunsproject.food_recipe_stevdza.adapter.PagerAdapter
import com.mamunsproject.food_recipe_stevdza.ui.fragments.ingredients.IngredientsFragment
import com.mamunsproject.food_recipe_stevdza.ui.fragments.instruction.InstructionFragment
import com.mamunsproject.food_recipe_stevdza.ui.fragments.overview.OverViewFragment
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.place_holder_row_layout.*

class DetailsActivity : AppCompatActivity() {

    // To get parsed variables from Home Fragment
    private val args by navArgs<DetailsActivityArgs>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        setSupportActionBar(toolbar)
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val fragments = ArrayList<Fragment>()
        fragments.add(OverViewFragment())
        fragments.add(IngredientsFragment())
        fragments.add(InstructionFragment())


        val titles = ArrayList<String>()
        titles.add("OverView")
        titles.add("Ingredients")
        titles.add("Instructions")

        val resultBundle = Bundle()
        resultBundle.putParcelable("recipeBundle", args.result)

        val adapter = PagerAdapter(
            resultBundle,
            fragments,
            titles,
            supportFragmentManager
        )

        viewpager.adapter = adapter
        tablayout.setupWithViewPager(viewpager)

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}