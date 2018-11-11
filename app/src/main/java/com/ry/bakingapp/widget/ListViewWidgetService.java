package com.ry.bakingapp.widget;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.widget.RemoteViewsService;

import com.ry.bakingapp.models.RecipeCard;
import com.ry.bakingapp.models.RecipeIngredient;
import com.ry.bakingapp.remote.ApiUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by netserve on 11/11/2018.
 */

public class ListViewWidgetService extends RemoteViewsService {
    public static final String TAG = ListViewWidgetService.class.getSimpleName();
    private List<RecipeCard> recipeCards;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate: " );
    }

    public RemoteViewsService.RemoteViewsFactory onGetViewFactory(final Intent intent) {
        Log.e(TAG, "onGetViewFactory: ");

        recipeCards = new ArrayList<>();
        List<RecipeIngredient> recipeIngredients1 = new ArrayList<>();
//        recipeIngredients1.add(new RecipeIngredient())
        recipeCards.add(new RecipeCard(1, "Nutella Pie", new ArrayList<RecipeIngredient>()));
        recipeCards.add(new RecipeCard(2, "Brownies", new ArrayList<RecipeIngredient>()));
        recipeCards.add(new RecipeCard(3, "Yellow Cake", new ArrayList<RecipeIngredient>()));
        recipeCards.add(new RecipeCard(4, "Cheesecake", new ArrayList<RecipeIngredient>()));

        return new ListViewRemoteViewsFactory(getApplicationContext(), intent, recipeCards);

    }
    /**/

}
