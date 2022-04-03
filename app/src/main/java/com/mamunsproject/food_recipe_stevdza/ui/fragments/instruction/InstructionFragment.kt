package com.mamunsproject.food_recipe_stevdza.ui.fragments.instruction

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import com.mamunsproject.food_recipe_stevdza.R
import com.mamunsproject.food_recipe_stevdza.models.Result_
import com.mamunsproject.food_recipe_stevdza.utils.Constant.Companion.RECIPE_RESULT_KEY
import kotlinx.android.synthetic.main.fragment_instruction.view.*


class InstructionFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_instruction, container, false)


        val args = arguments
        val myBundle: Result_? = args?.getParcelable(RECIPE_RESULT_KEY)
        view.instruction_webView.webViewClient = object : WebViewClient() {}

        val websiteUrl: String = myBundle!!.sourceUrl

        view.instruction_webView.loadUrl(websiteUrl)



        return view;
    }


}