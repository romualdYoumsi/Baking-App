package com.ry.bakingapp.pages.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;

import com.ry.bakingapp.R;
import com.ry.bakingapp.adapter.RecipeCardAdapter;
import com.ry.bakingapp.listeners.FindRecipeCardListener;
import com.ry.bakingapp.listeners.RecipeCardAdapterListener;
import com.ry.bakingapp.models.RecipeCard;
import com.ry.bakingapp.pages.detail.DetailActivity;
import com.ry.bakingapp.task.FindRecipeCardTask;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FindRecipeCardListener, RecipeCardAdapterListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private List<RecipeCard> mRecipeCardList;

    private RecyclerView mRecyclerView;
    private RecipeCardAdapter mRecipeCardAdapter;


    public int calculateNoOfColumns() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (dpWidth / 200);
        return noOfColumns;
    }

//    find recipe card task
    private FindRecipeCardTask mFindRecipeCardTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (mFindRecipeCardTask == null) {
            mFindRecipeCardTask = new FindRecipeCardTask(MainActivity.this, MainActivity.this);
            mFindRecipeCardTask.execute();
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_recipecard);

        mRecipeCardList = new ArrayList<>();

        mRecipeCardAdapter = new RecipeCardAdapter(MainActivity.this, mRecipeCardList, MainActivity.this);

        GridLayoutManager mLayoutManager = new GridLayoutManager(MainActivity.this, calculateNoOfColumns());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mRecipeCardAdapter);
    }

    @Override
    public void onFindRecipeCardComplete(List<RecipeCard> recipeCards) {
        if (recipeCards == null) {
            Toast.makeText(MainActivity.this, "Failed to get data. Please check your internet connection.", Toast.LENGTH_LONG).show();
            return;
        }

        Log.e(TAG, "onFindRecipeCardComplete: size="+recipeCards.size()+
                " 0name="+recipeCards.get(0).getName());
        if (recipeCards.size() == 0) {
            Toast.makeText(MainActivity.this, "No data found, please try again later.", Toast.LENGTH_LONG).show();
            return;
        }

        mRecipeCardList.addAll(recipeCards);
        mRecipeCardAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRecipecardClickListener(RecipeCard recipeCard) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("recipeItem", recipeCard);
        Log.e(TAG, "onRecipecardClickListener: name+"+recipeCard.getName() );
        startActivity(intent);
    }
}
