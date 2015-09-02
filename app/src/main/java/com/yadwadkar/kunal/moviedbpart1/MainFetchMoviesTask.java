package com.yadwadkar.kunal.moviedbpart1;

import android.os.AsyncTask;
import android.util.Log;

import com.yadwadkar.kunal.moviedbpart1.api.models.DiscoveredMovies;
import com.yadwadkar.kunal.moviedbpart1.api.models.Movie;
import com.yadwadkar.kunal.moviedbpart1.api.utils.MovieDataProvider;

/**
 * Handler for background task on the app main screen.
 *
 * Created by kyadwadkar on 8/22/15.
 */
public class MainFetchMoviesTask extends AsyncTask<Void, Void, DiscoveredMovies> {

    public static final String LOG_TAG = MainFetchMoviesTask.class.getCanonicalName();
    private MovieDataProvider movieDataProvider;
    private ImageAdapter adapter;

    public MainFetchMoviesTask(MovieDataProvider movieDataProvider) {
        this.movieDataProvider = movieDataProvider;
    }

    public void setAdapter(ImageAdapter adapter) {
        this.adapter = adapter;
    }

    public void setSortOrder(MovieDataProvider.Sort sortOrder) {
        movieDataProvider.setSortOrder(sortOrder);
    }

    @Override
    protected DiscoveredMovies doInBackground(Void... params) {
        try {
            return movieDataProvider.getMovies();
        } catch (Exception e) {
            Log.e(LOG_TAG, "Unable to get any movies");
        }
        return new DiscoveredMovies();
    }

    @Override
    protected void onPostExecute(DiscoveredMovies discoveredMovies) {

        Log.d(LOG_TAG, "Done with execute");
        if (discoveredMovies != null && discoveredMovies.getMoviesList() != null) {
            Log.d(LOG_TAG, discoveredMovies.getMoviesList().toString());
            adapter.clear();
            adapter.setNotifyOnChange(false);
            for (Movie movie : discoveredMovies.getMoviesList()) {
                adapter.add(movie);
            }
            adapter.notifyDataSetChanged();
        }
    }
}
