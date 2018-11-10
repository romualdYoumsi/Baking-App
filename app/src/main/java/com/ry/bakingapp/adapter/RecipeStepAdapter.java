package com.ry.bakingapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ry.bakingapp.R;
import com.ry.bakingapp.listeners.RecipeStepAdapterListener;
import com.ry.bakingapp.models.RecipeStep;

import java.util.List;

/**
 * Created by netserve on 10/11/2018.
 */

public class RecipeStepAdapter  extends RecyclerView.Adapter<RecipeStepAdapter.RecipeStepViewHolder> {
    private static final String TAG = RecipeStepAdapter.class.getSimpleName();

    private Context mContext;
    private List<RecipeStep> mRecipeSteps;
    private RecipeStepAdapterListener listener;

    public RecipeStepAdapter(Context context, List<RecipeStep> recipeSteps, RecipeStepAdapterListener listener) {
        this.mContext = context;
        this.mRecipeSteps = recipeSteps;
        this.listener = listener;
    }

    @Override
    public RecipeStepViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_recipe_step, parent, false);

        return new RecipeStepAdapter.RecipeStepViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecipeStepViewHolder holder, int position) {

//        Log.e(TAG, "onBindViewHolder: name="+mRecipeSteps.get(position).getName());
        holder.shortDescriptionTV.setText(mRecipeSteps.get(position).getShortDescription());
    }

    @Override
    public int getItemCount() {
        if (mRecipeSteps != null) {
            return mRecipeSteps.size();
        }

        return 0;
    }

    //    ViewHolder de l'adapter
    public class RecipeStepViewHolder extends RecyclerView.ViewHolder {
        public TextView shortDescriptionTV;

        public RecipeStepViewHolder(View itemView) {
            super(itemView);

            shortDescriptionTV = (TextView) itemView.findViewById(R.id.tv_recipestep_shortdesc);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onRecipeStepClickListener(mRecipeSteps.get(getAdapterPosition()), getAdapterPosition());
                }
            });
        }
    }
}
