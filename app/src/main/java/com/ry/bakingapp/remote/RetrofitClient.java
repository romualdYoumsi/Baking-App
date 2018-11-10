package com.ry.bakingapp.remote;

import android.content.Context;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by netserve on 03/08/2018.
 */

public class RetrofitClient {

    public static final String TAG = RetrofitClient.class.getSimpleName();
    private static Retrofit retrofit = null;

    public static Retrofit getClient(final Context context, String url) {
        if (retrofit == null) {

            OkHttpClient httpClient = new OkHttpClient.Builder()
                    .readTimeout(120, TimeUnit.SECONDS)
                    .connectTimeout(120, TimeUnit.SECONDS)
                    .build();

            retrofit = new Retrofit.Builder()
                    .client(httpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(url)
                    .build();
        }
        return retrofit;
    }
}
