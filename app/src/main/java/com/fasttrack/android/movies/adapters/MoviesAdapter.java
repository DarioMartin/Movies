package com.fasttrack.android.movies.adapters;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.fasttrack.android.movies.R;
import com.fasttrack.android.movies.data.MoviesContract;
import com.fasttrack.android.movies.models.Movie;
import com.fasttrack.android.movies.utils.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dariomartin on 3/4/17.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private ArrayList<Movie> movies;
    private Cursor cursor;
    private PositionClickListener positionClickListener;

    public MoviesAdapter() {
        movies = new ArrayList<>();
    }

    public void clearContent() {
        movies.clear();
        cursor = null;
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
        if (cursor == null) {
            holder.setItem(movies.get(position));
        } else if (cursor.getCount() > position) {
            holder.setItem(getCursorMovieAtPosition(position));
        }
    }

    @Override
    public int getItemCount() {
        if (cursor == null) {
            return movies.size();
        }
        return cursor.getCount();
    }

    public void addMovieList(List<Movie> movies) {
        cursor = null;
        this.movies.addAll(movies);
    }

    public void addMovieCursor(Cursor cursor) {
        this.cursor = cursor;
        this.movies.clear();
    }

    public void setPositionClickListener(PositionClickListener positionClickListener) {
        this.positionClickListener = positionClickListener;
    }

    public Movie getMovieAtPosition(int position) {
        if (cursor == null) {
            return movies.isEmpty() ? null : movies.get(position);
        } else if (cursor.getCount() > position) {
            return getCursorMovieAtPosition(position);
        }
        return null;
    }

    private Movie getCursorMovieAtPosition(int position) {
        int tmbdIdIndex = cursor.getColumnIndex(MoviesContract.FavMoviesEntry.COLUMN_TMDB_ID);
        int titleIndex = cursor.getColumnIndex(MoviesContract.FavMoviesEntry.COLUMN_TITLE);
        int posterIndex = cursor.getColumnIndex(MoviesContract.FavMoviesEntry.COLUMN_POSTER);

        cursor.moveToPosition(position);

        Movie movie = new Movie();
        movie.setId(cursor.getString(tmbdIdIndex));
        movie.setTitle(cursor.getString(titleIndex));
        movie.setPoster(cursor.getString(posterIndex));

        return movie;
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
