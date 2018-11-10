package com.ry.bakingapp.listeners;

import com.ry.bakingapp.models.RecipeCard;

import java.util.List;

/**
 * Created by netserve on 09/11/2018.
 */

public interface FindRecipeCardListener {
    void onFindRecipeCardComplete(List<RecipeCard> recipeCards);
}
