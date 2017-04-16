package com.fasttrack.android.movies.utils;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by dariomartin on 6/4/17.
 */

public class ImageLoader {

    public static void loadImage(final Context context, final String url, final ImageView view) {
        Picasso.with(context).load(url).into(view);
    }

}
