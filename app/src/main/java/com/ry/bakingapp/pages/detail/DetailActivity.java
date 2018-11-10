package com.ry.bakingapp.pages.detail;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.ry.bakingapp.R;
import com.ry.bakingapp.listeners.RecipeStepAdapterListener;
import com.ry.bakingapp.models.RecipeCard;
import com.ry.bakingapp.models.RecipeStep;
import com.ry.bakingapp.pages.detail.fragment.RecipeStepDetailFragment;
import com.ry.bakingapp.pages.detail.fragment.RecipeStepFragment;
import com.ry.bakingapp.pages.detailstep.DetailStepActivity;

public class DetailActivity extends AppCompatActivity {
    private static final String TAG = DetailActivity.class.getSimpleName();
    private RecipeCard mRecipeCard;

    private boolean isLayoutInDualPaneMode(boolean show_dual_pane){

        FrameLayout frameLayout = findViewById(R.id.details_frame);
        if(frameLayout != null) {
            if(show_dual_pane)
                frameLayout.setVisibility(FrameLayout.VISIBLE);
            else
                frameLayout.setVisibility(FrameLayout.GONE);
        }
        return  frameLayout != null;
    }

    private void setUpPane() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if(isLayoutInDualPaneMode(true)){
            Log.e(TAG, "setUpPane: true" );
            final RecipeStepDetailFragment detailsFragment = RecipeStepDetailFragment.newInstance(null);
            fragmentTransaction.replace(R.id.master_frame, RecipeStepFragment.newInstance(mRecipeCard, new RecipeStepAdapterListener() {
                @Override
                public void onRecipeStepClickListener(RecipeStep recipeStep, int position) {
                    Log.e(TAG, "onRecipeStepClickListener: "+position );
                    detailsFragment.onRecipeStepClickListener(recipeStep, position);
                }
            }));
            fragmentTransaction.replace(R.id.details_frame, detailsFragment);

        } else {
            Log.e(TAG, "setUpPane: false" );
            fragmentTransaction.replace(R.id.master_frame, RecipeStepFragment.newInstance(mRecipeCard, new RecipeStepAdapterListener() {
                @Override
                public void onRecipeStepClickListener(RecipeStep recipeStep, int position) {
                    Log.e(TAG, "onRecipeStepClickListener: getShortDescription="+recipeStep.getShortDescription());
                    Intent intent = new Intent(DetailActivity.this, DetailStepActivity.class);
                    intent.putExtra("recipeSteps", mRecipeCard);
                    intent.putExtra("position", position);
                    startActivity(intent);
                }
            }));
        }

        fragmentTransaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mRecipeCard = (RecipeCard) getIntent().getExtras().getParcelable("recipeItem");

        Log.e(TAG, "onCreate: mRecipe=" + mRecipeCard.getName());
        setTitle(mRecipeCard.getName());

        setUpPane();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
