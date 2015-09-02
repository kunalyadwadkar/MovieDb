package com.yadwadkar.kunal.moviedbpart1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.yadwadkar.kunal.moviedbpart1.api.configuration.Constants;
import com.yadwadkar.kunal.moviedbpart1.api.models.Movie;

import java.util.List;

/**Lo
 * Created by kyadwadkar on 8/22/15.
 */
public class ImageAdapter extends ArrayAdapter<Movie> {

    private static final String LOG_TAG = ImageAdapter.class.getSimpleName();


    public ImageAdapter(Context context, List<Movie> objects) {
        super(context, 0, objects);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.movie_item, parent, false);
        }


        ImageView imageView = (ImageView) convertView.findViewById(R.id.movie_img_item);


        StringBuilder url = new StringBuilder(Constants.IMAGE_URL_BASE);
        url.append(getItem(position).getPosterPath());


        Picasso.with(getContext()).load(url.toString()).resize(190, 270).centerCrop().into(imageView);

        return imageView;
    }

}
