package com.fasttrack.android.movies.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fasttrack.android.movies.R;
import com.fasttrack.android.movies.models.Movie;
import com.fasttrack.android.movies.views.viewHolders.MovieViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dariomartin on 3/4/17.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private ArrayList<Movie> movies;
    private View.OnClickListener onItemClickListener;

    public MoviesAdapter() {
        this.movies = new ArrayList<>();
    }


    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card, parent, false);
        view.setOnClickListener(onItemClickListener);
        MovieViewHolder viewHolder = new MovieViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.setItem(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void addMovies(List<Movie> movies) {
        this.movies.addAll(movies);
    }

    public void setOnItemClickListener(View.OnClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public Movie getMovieAtPositon(int position) {
        return movies.isEmpty() ? null : movies.get(position);
    }
}
