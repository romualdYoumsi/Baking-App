package com.ry.bakingapp.task;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.ry.bakingapp.listeners.FindRecipeCardListener;
import com.ry.bakingapp.models.RecipeCard;
import com.ry.bakingapp.remote.ApiUtils;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by netserve on 09/11/2018.
 */

public class FindRecipeCardTask extends AsyncTask<Void, Void, List<RecipeCard>> {
    private static final String TAG = FindRecipeCardTask.class.getSimpleName();

    private Context mContext;
    private FindRecipeCardListener task;

    public FindRecipeCardTask(Context context, FindRecipeCardListener findRecipeCardListener) {
        this.mContext = context;
        this.task = findRecipeCardListener;
    }

    @Override
    protected List<RecipeCard> doInBackground(Void... voids) {
        Call<List<RecipeCard>> call = ApiUtils.getBakingService(mContext).findRecipesCard();
        try {
            Response<List<RecipeCard>> response = call.execute();
            if (response.isSuccessful()) {
                List<RecipeCard> recipeCards = response.body();
                Log.e(TAG, "doInBackground: products=" + recipeCards.size());

                return recipeCards;
            } else {
                String error = null;
                try {
                    error = response.errorBody().string();
                    Log.e(TAG, "doInBackground: onResponse err: " + error + " code=" + response.code());

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(List<RecipeCard> recipeCards) {
//        super.onPostExecute(recipeCards);
//        Log.e(TAG, "onPostExecute: products=" + recipeCards.size());
        task.onFindRecipeCardComplete(recipeCards);
    }
}
