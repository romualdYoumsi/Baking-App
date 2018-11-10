package com.ry.bakingapp.pages.detailstep;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.ry.bakingapp.R;
import com.ry.bakingapp.models.RecipeCard;
import com.ry.bakingapp.models.RecipeStep;
import com.ry.bakingapp.pages.detail.fragment.RecipeStepDetailFragment;

import java.util.List;

public class DetailStepActivity extends AppCompatActivity {
    private RecipeCard mRecipeCard;
    private int mPosition;
    private int currentPosition;

    private Button nextBtn, prevbtn;
    private void inflateRecipeStepDetail(int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

//        update the title bar
        setTitle(mRecipeCard.getSteps().get(position).getShortDescription());

        fragmentTransaction.replace(R.id.master_frame, RecipeStepDetailFragment.newInstance(mRecipeCard.getSteps().get(position)));
        fragmentTransaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_step);

        mRecipeCard = getIntent().getExtras().getParcelable("recipeSteps");
        mPosition = getIntent().getExtras().getInt("position");

        currentPosition = mPosition;

        prevbtn = (Button) findViewById(R.id.btn_prev);
        nextBtn = (Button) findViewById(R.id.btn_next);

//        prev button click listener
        prevbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentPosition > 0) {
                    currentPosition--;
                    inflateRecipeStepDetail(currentPosition);
                }
            }
        });
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentPosition < (mRecipeCard.getSteps().size()-1)) {
                    currentPosition++;
                    inflateRecipeStepDetail(currentPosition);
                }
            }
        });
        inflateRecipeStepDetail(currentPosition);
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
