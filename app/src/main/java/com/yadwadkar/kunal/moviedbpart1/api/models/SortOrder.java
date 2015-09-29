package com.yadwadkar.kunal.moviedbpart1.api.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.yadwadkar.kunal.moviedbpart1.api.utils.MovieDataProvider;

/**
 * Created by kyadwadkar on 9/28/15.
 */
public class SortOrder implements Parcelable {

    private MovieDataProvider.Sort sortOrder;

    public MovieDataProvider.Sort getValue() {
        return sortOrder;
    }
    public SortOrder(MovieDataProvider.Sort sortOrder) {
        this.sortOrder = sortOrder;
    }

    private SortOrder(Parcel in) {

        String sortOrderStr = in.readString();

        for (MovieDataProvider.Sort sort : MovieDataProvider.Sort.values()) {
            if (sort.getValue().equals(sortOrderStr)) {
                this.sortOrder = sort;
                break;
            }
        }

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(sortOrder.getValue());
    }

    public static final Parcelable.Creator<SortOrder> CREATOR = new Parcelable.Creator<SortOrder>(){

        @Override
        public SortOrder createFromParcel(Parcel source) {
            return new SortOrder(source);
        }

        @Override
        public SortOrder[] newArray(int size) {
            return new SortOrder[size];
        }
    };
}
