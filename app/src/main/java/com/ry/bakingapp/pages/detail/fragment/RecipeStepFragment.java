package com.ry.bakingapp.pages.detail.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ry.bakingapp.R;
import com.ry.bakingapp.adapter.RecipeStepAdapter;
import com.ry.bakingapp.listeners.RecipeStepAdapterListener;
import com.ry.bakingapp.models.RecipeCard;
import com.ry.bakingapp.models.RecipeIngredient;
import com.ry.bakingapp.models.RecipeStep;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeStepFragment extends Fragment {
    private static final String TAG = RecipeStepFragment.class.getSimpleName();

    private static RecipeCard mRecipeCard;
    private static RecipeStepAdapterListener recipeStepAdapterListener;


    private RecyclerView mRecyclerViewSteps;
    private TextView ingredientsTV;

    private List<RecipeStep> recipeStepList;
    private RecipeStepAdapter mAdapter;

    public RecipeStepFragment() {
        // Required empty public constructor
    }

    private void initIngredients() {
        String stringIngredients = "";
        for (int i = 0; i < mRecipeCard.getIngredients().size(); i++) {
            RecipeIngredient ingredient = mRecipeCard.getIngredients().get(i);
            stringIngredients += String.format(", %s (%s X %s)", ingredient.getIngredient(), ingredient.getQuantity(), ingredient.getMeasure());
        }

        ingredientsTV.setText(stringIngredients);
    }

    private void initDescription() {
        recipeStepList.addAll(mRecipeCard.getSteps());

        mAdapter.notifyDataSetChanged();
    }


    public static RecipeStepFragment newInstance(RecipeCard recipeCard, RecipeStepAdapterListener listener) {
        mRecipeCard = recipeCard;
        recipeStepAdapterListener = listener;

        Bundle args = new Bundle();
        RecipeStepFragment fragment = new RecipeStepFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView: " );
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_recipe_step, container, false);
        ingredientsTV = (TextView) rootView.findViewById(R.id.tv_recipestep_ingredient);
        mRecyclerViewSteps = (RecyclerView) rootView.findViewById(R.id.rv_recipestep);

        recipeStepList = new ArrayList<>();

        mAdapter = new RecipeStepAdapter(getContext(), recipeStepList, recipeStepAdapterListener);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerViewSteps.setLayoutManager(mLayoutManager);
        mRecyclerViewSteps.setItemAnimator(new DefaultItemAnimator());
        mRecyclerViewSteps.setAdapter(mAdapter);

        initIngredients();
        initDescription();

        return rootView;
    }
}
