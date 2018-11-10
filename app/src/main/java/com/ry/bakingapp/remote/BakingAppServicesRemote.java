package com.ry.bakingapp.remote;

import com.ry.bakingapp.models.RecipeCard;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by netserve on 03/08/2018.
 */

public interface BakingAppServicesRemote {

    //  Recupération de la liste des recipes
    @GET("baking.json")
    Call<List<RecipeCard>> findRecipesCard();
}
