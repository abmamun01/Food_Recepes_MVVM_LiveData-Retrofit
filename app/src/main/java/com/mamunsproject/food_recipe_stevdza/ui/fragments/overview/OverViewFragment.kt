package com.mamunsproject.food_recipe_stevdza.ui.fragments.overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import coil.load
import com.mamunsproject.food_recipe_stevdza.R
import com.mamunsproject.food_recipe_stevdza.models.Result_
import com.mamunsproject.food_recipe_stevdza.utils.Constant
import com.mamunsproject.food_recipe_stevdza.utils.Constant.Companion.RECIPE_RESULT_KEY
import kotlinx.android.synthetic.main.fragment_over_view.view.*
import org.jsoup.Jsoup


class OverViewFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_over_view, container, false)


        val args = arguments
        val myBundle: Result_? = args?.getParcelable(RECIPE_RESULT_KEY)

        view.mainImage.load(myBundle?.image)
        view.title_textView.text = myBundle?.title
        view.like_textView.text = myBundle?.aggregateLikes.toString()
        view.timer_textView.text = myBundle?.readyInMinutes.toString()
        view.summary_text.text = myBundle?.summary
        myBundle?.summary.let {
            val summary = Jsoup.parse(it).text()
            view.summary_text.text = summary
        }

        if (myBundle?.vegetarian == true) {
            view.vegetarian_imageView.setColorFilter(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.green
                )
            )
            view.vegetarian_textview.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.green
                )
            )
        }

        if (myBundle?.vegan == true) {
            view.vegan_imageview.setColorFilter(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.green
                )
            )
            view.vegan_tv.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
        }

        if (myBundle?.glutenFree == true) {
            view.glutenfree_iv.setColorFilter(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.green
                )
            )
            view.glutenfree_tv.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
        }


        if (myBundle?.dairyFree == true) {
            view.dairyfree_iv.setColorFilter(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.green
                )
            )
            view.dairyfree_tv.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
        }



        if (myBundle?.veryHealthy == true) {
            view.healthy_iv.setColorFilter(ContextCompat.getColor(requireContext(), R.color.green))
            view.healthy_tv.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
        }


        if (myBundle?.cheap == true) {
            view.cheap_iv.setColorFilter(ContextCompat.getColor(requireContext(), R.color.green))
            view.cheap_tv.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
        }




        return view;
    }


}