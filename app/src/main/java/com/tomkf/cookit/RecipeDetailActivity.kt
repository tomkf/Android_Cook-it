package com.tomkf.cookit

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_recipe_detail.*
import kotlinx.android.synthetic.main.recipe_step_list.view.*

class RecipeDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_detail)
        val recipe: Recipe = intent?.extras?.getParcelable(recipeExtra)!!
        recipe_name.text = recipe.name
        Glide.with(this).load(recipe.imageURL).into(recipe_image)
        recipe_steps.layoutManager = LinearLayoutManager(this)
        recipe_steps.adapter = StepsAdapter(recipe.steps, this)
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

private class StepsAdapter(
    val steps: List<String>,
    val context: Context
) :
    RecyclerView.Adapter<StepsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepsViewHolder {
        return StepsViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.recipe_step_list,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return steps.count()
    }

    override fun onBindViewHolder(holder: StepsViewHolder, position: Int) {
        val step = steps[position]
        holder.itemView.recipe_step.text = step
    }
}

private class StepsViewHolder(view: View) : RecyclerView.ViewHolder(view)