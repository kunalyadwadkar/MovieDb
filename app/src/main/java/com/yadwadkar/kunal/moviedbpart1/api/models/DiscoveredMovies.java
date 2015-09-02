package com.yadwadkar.kunal.moviedbpart1.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by kyadwadkar on 8/17/15.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class DiscoveredMovies {

    int page;

    @JsonProperty("results")
    List<Movie> moviesList;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Movie> getMoviesList() {
        return moviesList;
    }

    public void setMoviesList(List<Movie> moviesList) {
        this.moviesList = moviesList;
    }


}
