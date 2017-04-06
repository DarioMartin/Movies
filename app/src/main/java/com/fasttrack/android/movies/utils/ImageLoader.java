package com.fasttrack.android.movies.utils;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by megamedia on 6/4/17.
 */

public class ImageLoader {

    public static void loadImage(Context context, String url, ImageView view){
        Picasso.with(context).load(url).into(view);
    }

}
