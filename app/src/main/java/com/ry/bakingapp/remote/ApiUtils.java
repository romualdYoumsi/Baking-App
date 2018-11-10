package com.ry.bakingapp.remote;

import android.content.Context;

/**
 * Created by netserve on 03/08/2018.
 */

public final class ApiUtils {
    public static final String BASE_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/";

    private ApiUtils() {
    }

    //get an instance of Movies API services
    public static BakingAppServicesRemote getBakingService(Context context) {
        return RetrofitClient.getClient(context, BASE_URL).create(BakingAppServicesRemote.class);
    }

}
