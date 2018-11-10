package com.ry.bakingapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ry.bakingapp.R;
import com.ry.bakingapp.listeners.RecipeCardAdapterListener;
import com.ry.bakingapp.models.RecipeCard;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by netserve on 09/11/2018.
 */

public class RecipeCardAdapter extends RecyclerView.Adapter<RecipeCardAdapter.RecipeCardViewHolder> {
    private static final String TAG = RecipeCardAdapter.class.getSimpleName();

    private Context mContext;
    private List<RecipeCard> mRecipeCards;
    private RecipeCardAdapterListener listener;

    public RecipeCardAdapter(Context context, List<RecipeCard> recipeCards, RecipeCardAdapterListener listener) {
        this.mContext = context;
        this.mRecipeCards = recipeCards;
        this.listener = listener;
    }

    @Override
    public RecipeCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_recipecard, parent, false);

        return new RecipeCardAdapter.RecipeCardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecipeCardViewHolder holder, int position) {

//        Log.e(TAG, "onBindViewHolder: name="+mRecipeCards.get(position).getName());
        holder.nameTV.setText(mRecipeCards.get(position).getName());
    }

    @Override
    public int getItemCount() {
        if (mRecipeCards != null) {
            return mRecipeCards.size();
        }

        return 0;
    }

    //    ViewHolder de l'adapter
    public class RecipeCardViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTV;

        public RecipeCardViewHolder(View itemView) {
            super(itemView);

            nameTV = (TextView) itemView.findViewById(R.id.tv_recipecard_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onRecipecardClickListener(mRecipeCards.get(getAdapterPosition()));
                }
            });
        }
    }
}
