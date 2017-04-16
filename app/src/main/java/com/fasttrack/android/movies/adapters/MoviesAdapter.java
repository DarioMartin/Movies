package com.fasttrack.android.movies.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.fasttrack.android.movies.R;
import com.fasttrack.android.movies.models.Movie;
import com.fasttrack.android.movies.utils.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dariomartin on 3/4/17.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private ArrayList<Movie> movies;
    private PositionClickListener positionClickListener;

    public MoviesAdapter() {
        this.movies = new ArrayList<>();
    }

    public void clearContent() {
        movies.clear();
    }

    public interface PositionClickListener {
        void itemClicked(int position);
    }


    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {
        holder.setItem(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void addMovies(List<Movie> movies) {
        this.movies.addAll(movies);
    }

    public void setPositionClickListener(PositionClickListener positionClickListener) {
        this.positionClickListener = positionClickListener;
    }

    public Movie getMovieAtPosition(int position) {
        return movies.isEmpty() ? null : movies.get(position);
    }

    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView poster;

        MovieViewHolder(View itemView) {
            super(itemView);
            poster = (ImageView) itemView.findViewById(R.id.poster);
            itemView.setOnClickListener(this);
        }

        void setItem(Movie movie) {
            ImageLoader.loadImage(poster.getContext(), movie.getMediumPoster(), poster);
        }

        @Override
        public void onClick(View v) {
            positionClickListener.itemClicked(getAdapterPosition());
        }
    }
}
