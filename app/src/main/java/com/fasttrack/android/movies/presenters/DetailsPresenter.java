package com.fasttrack.android.movies.presenters;

import com.fasttrack.android.movies.connections.Controller;
import com.fasttrack.android.movies.models.Movie;
import com.fasttrack.android.movies.models.MovieImages;
import com.fasttrack.android.movies.views.DetailsActivity;
import com.fasttrack.android.movies.views.MainView;

import java.util.List;

/**
 * Created by dariomartin on 3/4/17.
 */

public class DetailsPresenter {

    private Movie movie;
    private DetailsActivity view;

    public DetailsPresenter(DetailsActivity view, Movie movie) {
        this.view = view;
        this.movie = movie;
    }

    public void getMovieImages() {
        Controller.getInstance().loadMovieImages(movie.getId(), new Controller.RequestCallback<MovieImages>() {
            @Override
            public void onResponse(MovieImages movieImages) {
                view.showMovieImages(movieImages);
            }

            @Override
            public void onFailure(String message) {

            }
        });
    }

}