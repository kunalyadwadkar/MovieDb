package com.yadwadkar.kunal.moviedbpart1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yadwadkar.kunal.moviedbpart1.api.configuration.Constants;
import com.yadwadkar.kunal.moviedbpart1.api.models.Movie;

import java.util.Calendar;


/**
 * A placeholder fragment containing a simple view.
 */
public class MovieDetailsActivityFragment extends Fragment {

    public MovieDetailsActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Movie movie = getActivity().getIntent().getParcelableExtra(MainActivityFragment.MOVIE_DETAILS_EXTRA);

        String titleString = movie.getOriginalTitle();

        View rootView = inflater.inflate(R.layout.fragment_movie_details, container, false);
        TextView titleText = (TextView) rootView.findViewById(R.id.title_text);
        titleText.setText(titleString);

        ImageView imageView = (ImageView) rootView.findViewById(R.id.movie_thumbnail);
        Picasso.with(getActivity().getApplicationContext()).load(Constants.IMAGE_URL_BASE
                + movie.getPosterPath()).into(imageView);
        TextView synopsis = (TextView) rootView.findViewById(R.id.plot_synopsis);
        synopsis.setText(movie.getPlotSynopsis());

        String rating = movie.getUserRating() + "/10";
        TextView ratingTextView = (TextView) rootView.findViewById(R.id.user_rating);
        ratingTextView.setText(rating);


        Calendar calendar = Calendar.getInstance();
        calendar.setTime(movie.getReleaseDate());

        String year = String.valueOf(calendar.get(Calendar.YEAR));
        TextView yearTextView = (TextView) rootView.findViewById(R.id.release_year);
        yearTextView.setText(year);
        return rootView;
    }
}
