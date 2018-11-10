package com.ry.bakingapp.listeners;

import com.ry.bakingapp.models.RecipeStep;

/**
 * Created by netserve on 10/11/2018.
 */

public interface RecipeStepAdapterListener {
    void onRecipeStepClickListener(RecipeStep recipeStep, int position);
}
