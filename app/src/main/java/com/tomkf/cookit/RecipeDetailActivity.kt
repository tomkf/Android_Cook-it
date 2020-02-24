package com.tomkf.cookit

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_recipe_detail.*

class RecipeDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_detail)

        // get the recipe from the intent, if null will throw NPE
        val recipe: Recipe = intent?.extras?.getParcelable(recipeExtra)!!

        recipe_name.text = recipe.name
    }

    companion object {
        const val recipeExtra = "RECIPE"

        fun newIntent(recipe: Recipe, context: Context): Intent {
            val intent = Intent(context, RecipeDetailActivity::class.java)
            intent.putExtra(recipeExtra, recipe)
            return intent
        }
    }
}