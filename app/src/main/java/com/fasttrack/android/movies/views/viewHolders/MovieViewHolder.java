package com.fasttrack.android.movies.views.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fasttrack.android.movies.R;
import com.fasttrack.android.movies.models.Movie;
import com.fasttrack.android.movies.utils.ImageLoader;

/**
 * Created by dariomartin on 3/4/17.
 */

public class MovieViewHolder extends RecyclerView.ViewHolder {

    private ImageView poster;

    public MovieViewHolder(View itemView) {
        super(itemView);
        poster = (ImageView) itemView.findViewById(R.id.poster);
    }

    public void setItem(Movie movie) {
        ImageLoader.loadImage(poster.getContext(), movie.getPoster(), poster);
    }
}
