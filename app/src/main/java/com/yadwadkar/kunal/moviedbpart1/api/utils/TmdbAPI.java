package com.yadwadkar.kunal.moviedbpart1.api.utils;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yadwadkar.kunal.moviedbpart1.api.configuration.Constants;
import com.yadwadkar.kunal.moviedbpart1.api.models.DiscoveredMovies;

import java.io.IOException;

/**
 * Utility to use the movie database api.
 * Created by kyadwadkar on 8/17/15.
 */
public class TmdbAPI implements MovieDataProvider {

    public static final String QUERY_PARAM_SORT = "sort_by";
    public static final String QUERY_PARAM_KEY = "api_key";
    public static final String LOG_TAG = TmdbAPI.class.getSimpleName();
    public static final String DISCOVERY_PATH = "discover/movie";

    private Sort sort = Sort.POPULARITY_DESC;
    private ObjectMapper jsonMapper;

    public TmdbAPI(ObjectMapper jsonMapper) {
        //TODO Check for DI framework (Dagger2 or Dagger)
        // Life is so crappy without dependency injection!!
        // :(
        this.jsonMapper = jsonMapper;
    }

    @Override
    public void setSortOrder(@NonNull Sort selectedSort) {
        this.sort = selectedSort;
    }

    @Override
    public DiscoveredMovies getMovies() {

        Uri.Builder apiUri = Uri.parse(Constants.URL_BASE)
                .buildUpon()
                .appendEncodedPath(DISCOVERY_PATH)
                .appendQueryParameter(QUERY_PARAM_KEY, Constants.API_KEY)
                .appendQueryParameter(QUERY_PARAM_SORT, sort.getValue());

        Log.d(LOG_TAG, apiUri.toString());
        String jsonResponse = NetworkUtil.getJsonResponse(apiUri.build());

        // TODO remove.
        Log.d(LOG_TAG, jsonResponse);

        try {
            return jsonMapper.readValue(jsonResponse, DiscoveredMovies.class);
        } catch (IOException e) {
            String errMsg = String.format("Unable to deserialize object from json. Reason %s ", e.getMessage());
            Log.e(LOG_TAG, errMsg);
            throw new RuntimeException(errMsg);
        }
    }
}
