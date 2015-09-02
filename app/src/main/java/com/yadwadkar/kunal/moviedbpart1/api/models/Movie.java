package com.yadwadkar.kunal.moviedbpart1.api.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by kyadwadkar on 8/17/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie implements Parcelable {

    @JsonProperty(value = "poster_path")
    private String posterPath;

    @JsonProperty(value = "original_title")
    private String originalTitle;

    @JsonProperty(value = "overview")
    private String plotSynopsis;

    @JsonProperty(value = "vote_average")
    private double userRating;

    @JsonProperty(value = "release_date")
    private Date releaseDate;

    public Movie() {

    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getPlotSynopsis() {
        return plotSynopsis;
    }

    public double getUserRating() {
        return userRating;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(posterPath);
        dest.writeString(originalTitle);
        dest.writeString(plotSynopsis);
        dest.writeDouble(userRating);
        dest.writeString(releaseDate.toString());
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {

        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };


    private Movie(Parcel in) {

        posterPath = in.readString();
        originalTitle = in.readString();
        plotSynopsis = in.readString();
        userRating = in.readDouble();

        SimpleDateFormat df = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy", Locale.US);
        try {
            releaseDate = df.parse(in.readString());
        } catch (ParseException e) {
            Log.e("Movie", "Unable to parse release date from parcel.", e);
        }
    }


}
