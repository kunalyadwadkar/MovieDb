package com.yadwadkar.kunal.moviedbpart1.api.utils;

import android.support.annotation.NonNull;

import com.yadwadkar.kunal.moviedbpart1.api.models.DiscoveredMovies;

/**
 * Created by kyadwadkar on 8/17/15.
 */
public interface MovieDataProvider {

    public enum Sort {
        POPULARITY_DESC("popularity.desc"),
        POPULARITY_ASC("popularity.asc"),
        RATING_DESC("vote_average.desc"),
        RATING_ASC("vote_average.asc");

        private String value;

        Sort(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    void setSortOrder(@NonNull Sort selectedSort);

    DiscoveredMovies getMovies();

}
