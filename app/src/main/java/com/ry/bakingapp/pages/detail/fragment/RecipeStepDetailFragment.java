package com.ry.bakingapp.pages.detail.fragment;


import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.devbrackets.android.exomedia.listener.OnPreparedListener;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.ry.bakingapp.R;
import com.ry.bakingapp.listeners.RecipeStepAdapterListener;
import com.ry.bakingapp.models.RecipeStep;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeStepDetailFragment extends Fragment implements RecipeStepAdapterListener, OnPreparedListener {
    private static final String TAG = RecipeStepDetailFragment.class.getSimpleName();

    private static RecipeStep mRecipeStep;

    private SimpleExoPlayer player;
    private com.devbrackets.android.exomedia.ui.widget.VideoView playerView;
    private TextView descriptionTV;
    public RecipeStepDetailFragment() {
        // Required empty public constructor
    }


    public static RecipeStepDetailFragment newInstance(RecipeStep recipeStep) {
        mRecipeStep = recipeStep;

        Bundle args = new Bundle();
        RecipeStepDetailFragment fragment = new RecipeStepDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }
    private void initDescription() {
        if (mRecipeStep != null) {
            descriptionTV.setText(mRecipeStep.getDescription());
        }
    }
    private void initRecipeVideo() {
        if (mRecipeStep != null) {
            Log.e(TAG, "initDescription: getVideoURL"+mRecipeStep.getVideoURL());
            if (mRecipeStep.getVideoURL() == null || mRecipeStep.getVideoURL().equals("")) {
                Toast.makeText(getContext(), "No video available.", Toast.LENGTH_SHORT).show();
                return;
            }
            playerView.setVideoURI(Uri.parse(mRecipeStep.getVideoURL()));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView: ");
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_recipe_step_detail, container, false);

        playerView = (com.devbrackets.android.exomedia.ui.widget.VideoView) rootView.findViewById(R.id.video_view);
        descriptionTV = (TextView) rootView.findViewById(R.id.tv_recipestep_detail_instruction);

        playerView.setOnPreparedListener(this);

        initDescription();
        initRecipeVideo();
        return rootView;
    }

    @Override
    public void onRecipeStepClickListener(RecipeStep recipeStep, int position) {
        mRecipeStep = recipeStep;
        Log.e(TAG, "onRecipeStepClickListener: position="+position);

        initDescription();
        initRecipeVideo();
    }

    @Override
    public void onPrepared() {
        playerView.start();
    }
}
