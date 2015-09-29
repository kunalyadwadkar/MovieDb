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
    private final MovieDataProvider movieDataProvider;
    private final ImageAdapter adapter;

    private MainFetchMoviesTask(MovieDataProvider movieDataProvider,
                               ImageAdapter adapter,
                               MovieDataProvider.Sort sortOrder) {
        this.movieDataProvider = movieDataProvider;
        this.adapter = adapter;
        this.movieDataProvider.setSortOrder(sortOrder);
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

    public static class Builder {
        private MovieDataProvider movieDataProvider;
        private MovieDataProvider.Sort sortOrder;
        private ImageAdapter imageAdapter;

        public Builder movieDataProvider(MovieDataProvider movieDataProvider) {
            this.movieDataProvider = movieDataProvider;
            return this;
        }

        public Builder sortOrder(MovieDataProvider.Sort sortOrder) {
            this.sortOrder = sortOrder;
            return this;
        }

        public Builder imageAdapter(ImageAdapter imageAdapter) {
            this.imageAdapter = imageAdapter;
            return this;
        }

        public MainFetchMoviesTask build() {
            return new MainFetchMoviesTask(movieDataProvider, imageAdapter, sortOrder);
        }
    }
}
