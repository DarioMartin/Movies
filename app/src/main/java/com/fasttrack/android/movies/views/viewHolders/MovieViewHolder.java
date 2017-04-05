package com.fasttrack.android.movies.views.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fasttrack.android.movies.R;
import com.fasttrack.android.movies.models.Movie;

/**
 * Created by dariomartin on 3/4/17.
 */

public class MovieViewHolder extends RecyclerView.ViewHolder {

    private TextView title;
    private ImageView poster;

    public MovieViewHolder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.title);
        poster = (ImageView) itemView.findViewById(R.id.poster);
    }

    public void setItem(Movie movie) {
        title.setText(movie.getTitle());
    }
}
