package com.ry.bakingapp.pages.detailstep;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ry.bakingapp.R;
import com.ry.bakingapp.models.RecipeCard;
import com.ry.bakingapp.pages.detail.fragment.RecipeStepDetailFragment;

public class DetailStepActivity extends AppCompatActivity {
    private static final String TAG = DetailStepActivity.class.getSimpleName();
    private RecipeCard mRecipeCard;
    private int mPosition;
    private int currentPosition = 0;

    private Button nextBtn, prevBtn;
    private TextView pageTV;

    private void inflateRecipeStepDetail(int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

//        update the title bar
        setTitle(mRecipeCard.getSteps().get(position).getShortDescription());

        fragmentTransaction.replace(R.id.master_frame, RecipeStepDetailFragment.newInstance(mRecipeCard.getSteps().get(position)));
        fragmentTransaction.commit();
    }

    private void updateTextPage(int page, int total) {
        pageTV.setText(String.format("Step %s / %s", page, total));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_step);

        if (getIntent().getExtras() != null) {
            mRecipeCard = getIntent().getExtras().getParcelable("recipeSteps");
            mPosition = getIntent().getExtras().getInt("position");
            currentPosition = mPosition;
        }

        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt("currentPosition");
        }

        prevBtn = (Button) findViewById(R.id.btn_prev);
        nextBtn = (Button) findViewById(R.id.btn_next);
        pageTV = (TextView) findViewById(R.id.tv_page);

//        prev button click listener
        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRecipeCard != null && currentPosition > 0) {
                    currentPosition--;
                    inflateRecipeStepDetail(currentPosition);
                    updateTextPage((currentPosition+1), mRecipeCard.getSteps().size());
                }
            }
        });
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRecipeCard != null && currentPosition < (mRecipeCard.getSteps().size()-1)) {
                    currentPosition++;
                    inflateRecipeStepDetail(currentPosition);
                    updateTextPage((currentPosition+1), mRecipeCard.getSteps().size());
                }
            }
        });

        if (mRecipeCard != null) {
            inflateRecipeStepDetail(currentPosition);
            updateTextPage((currentPosition+1), mRecipeCard.getSteps().size());
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
//        Log.e(TAG, "onSaveInstanceState: ");

        outState.putInt("currentPosition", currentPosition);
        super.onSaveInstanceState(outState);
    }

}
