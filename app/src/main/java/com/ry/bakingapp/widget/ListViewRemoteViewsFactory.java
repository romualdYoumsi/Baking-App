package com.ry.bakingapp.widget;

import android.annotation.SuppressLint;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.ry.bakingapp.R;
import com.ry.bakingapp.models.RecipeCard;
import com.ry.bakingapp.remote.ApiUtils;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by netserve on 11/11/2018.
 */

public class ListViewRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
    private static final String TAG = ListViewRemoteViewsFactory.class.getSimpleName();

    private Context mContext;

    private List<RecipeCard> records;


    public ListViewRemoteViewsFactory(Context context, Intent intent, List<RecipeCard> recipeCards) {

        mContext = context;
        records = recipeCards;

    }

    // Initialize the data set.

    public void onCreate() {
        Log.e(TAG, "onCreate: ");

        // In onCreate() you set up any connections / cursors to your data source. Heavy lifting,

        // for example downloading or creating content etc, should be deferred to onDataSetChanged()

        // or getViewAt(). Taking more than 20 seconds in this call will result in an ANR.

        /*
        records = new ArrayList<String>();
        records.add("toto");
        records.add("tata");
        records.add("tete");
        records.add("titi");
        records.add("tyty");
        records.add("toto");
        records.add("tata");
        records.add("tete");
        records.add("titi");
        records.add("tyty");
        records.add("toto");
        records.add("tata");
        records.add("tete");
        records.add("titi");
        records.add("tyty"); */

        /*
        if (records == null) {

            @SuppressLint("StaticFieldLeak")
            AsyncTask asyncTask = new AsyncTask<Object, Void, List<RecipeCard>>() {
                @Override
                protected List<RecipeCard> doInBackground(Object... objects) {

                    Call<List<RecipeCard>> call = ApiUtils.getBakingService(mContext).findRecipesCard();
                    try {
                        Response<List<RecipeCard>> response = call.execute();
                        if (response.isSuccessful()) {
                            List<RecipeCard> recipeCards = response.body();
                            Log.e(TAG, "doInBackground: products=" + recipeCards.size());

                            return recipeCards;
                        } else {
                            String error = null;
                            try {
                                error = response.errorBody().string();
                                Log.e(TAG, "doInBackground: onResponse err: " + error + " code=" + response.code());

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            return null;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        return null;
                    }
                }

                @Override
                protected void onPostExecute(List<RecipeCard> recipeCards) {
//                super.onPostExecute(recipeCards);

                    records = recipeCards;
                    onDataSetChanged();
                }
            };
        }
            asyncTask.execute(); */

    }

    // Given the position (index) of a WidgetItem in the array, use the item's text value in

    // combination with the app widget item XML file to construct a RemoteViews object.

    public RemoteViews getViewAt(int position) {
        Log.e(TAG, "getViewAt: position="+position );

        // position will always range from 0 to getCount() - 1.

        // Construct a RemoteViews item based on the app widget item XML file, and set the

        // text based on the position.

        RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.widget_item);

        // feed row

        RecipeCard data = records.get(position);

        rv.setTextViewText(R.id.item, data.getName());

        // end feed row

        // Next, set a fill-intent, which will be used to fill in the pending intent template

        // that is set on the collection view in ListViewWidgetProvider.

        Bundle extras = new Bundle();

        extras.putInt(RecipeIngredientsWidget.EXTRA_ITEM, position);

        Intent fillInIntent = new Intent();

        fillInIntent.putExtra("homescreen_meeting", data);

        fillInIntent.putExtras(extras);

        // Make it possible to distinguish the individual on-click

        // action of a given item

        rv.setOnClickFillInIntent(R.id.item_layout, fillInIntent);

        // Return the RemoteViews object.

        return rv;

    }

    public int getCount() {

        if (records != null) {
            Log.e("size=", records.size() + "");

            return records.size();
        }

        return 0;
    }

    public void onDataSetChanged() {
        Log.e(TAG, "onDataSetChanged: ");

        // Fetching JSON data from server and add them to records arraylist
        if (records != null) {
            Log.e(TAG, "onDataSetChanged: records size="+records.size() );
        }


    }

    public int getViewTypeCount() {

        return 1;

    }

    public long getItemId(int position) {

        return position;

    }

    public void onDestroy() {

        if (records != null) {
            records.clear();
        }

    }

    public boolean hasStableIds() {

        return true;

    }

    public RemoteViews getLoadingView() {

        return null;

    }

}

