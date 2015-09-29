package com.yadwadkar.kunal.moviedbpart1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yadwadkar.kunal.moviedbpart1.api.models.Movie;
import com.yadwadkar.kunal.moviedbpart1.api.models.SortOrder;
import com.yadwadkar.kunal.moviedbpart1.api.utils.MovieDataProvider;
import com.yadwadkar.kunal.moviedbpart1.api.utils.TmdbAPI;

import java.util.ArrayList;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public static final String LOG_TAG = MainActivityFragment.class.getSimpleName();
    public static final String MOVIE_DETAILS_EXTRA = "Movie_Details";
    public static final String SORT_KEY = "sort_key";

    private ImageAdapter imageAdapter;
    private MovieDataProvider.Sort sortOrder = MovieDataProvider.Sort.POPULARITY_DESC;


    public MainActivityFragment() {

    }

    @Override
    public void onStart() {
        super.onStart();

    }

    private void updateMovies() {
        MainFetchMoviesTask fetcher = new MainFetchMoviesTask.Builder()
                .movieDataProvider(new TmdbAPI(new ObjectMapper()))
                .imageAdapter(imageAdapter)
                .sortOrder(sortOrder)
                .build();

        fetcher.execute();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        if (savedInstanceState != null) {
            SortOrder sortOrder = savedInstanceState.getParcelable(SORT_KEY);
            this.sortOrder = sortOrder.getValue();
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(SORT_KEY, new SortOrder(sortOrder));
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_fragment_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.sort_menu_item) {
         ;
            if (sortOrder == MovieDataProvider.Sort.POPULARITY_DESC) {
                item.setTitle(R.string.sort_by_popularity);
                this.sortOrder = MovieDataProvider.Sort.RATING_DESC;
            } else {
                item.setTitle(R.string.sort_by_rating);
                this.sortOrder = MovieDataProvider.Sort.POPULARITY_DESC;

            }
            updateMovies();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem sortOptionMenuItem = menu.findItem(R.id.sort_menu_item);

        if (sortOrder == MovieDataProvider.Sort.POPULARITY_DESC) {
            sortOptionMenuItem.setTitle(R.string.sort_by_rating);
        } else {
            sortOptionMenuItem.setTitle(R.string.sort_by_popularity);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        imageAdapter = new ImageAdapter(getActivity().getApplicationContext(), new ArrayList<Movie>());



        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        GridView movieGrid = (GridView) rootView.findViewById(R.id.movieGrid);

        movieGrid.setAdapter(imageAdapter);
        imageAdapter.notifyDataSetChanged();


        try {
            updateMovies();
        } catch (Exception e) {
            Log.e("MainFragment", "Unable to get movies");
        }

        movieGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent detailsIntent = new Intent(getActivity().getApplicationContext(), MovieDetailsActivity.class);
                detailsIntent.putExtra(MOVIE_DETAILS_EXTRA, imageAdapter.getItem(position));
                startActivity(detailsIntent);

            }
        });

        return rootView;
    }
}
