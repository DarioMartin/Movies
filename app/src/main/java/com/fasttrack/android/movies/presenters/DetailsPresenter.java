package com.fasttrack.android.movies.presenters;

import com.fasttrack.android.movies.connections.Controller;
import com.fasttrack.android.movies.models.Movie;
import com.fasttrack.android.movies.models.MovieImages;
import com.fasttrack.android.movies.models.MovieReviews;
import com.fasttrack.android.movies.models.MovieVideos;
import com.fasttrack.android.movies.views.DetailsActivity;
import com.fasttrack.android.movies.views.DetailsView;

/**
 * Created by dariomartin on 3/4/17.
 */

public class DetailsPresenter {

    private Movie movie;
    private DetailsView view;

    public DetailsPresenter(DetailsView view, Movie movie) {
        this.view = view;
        this.movie = movie;
    }

    public void getMovieDetails(){
        Controller.getInstance().loadMovieDetails(movie.getId(), new Controller.RequestCallback<Movie>() {
            @Override
            public void onResponse(Movie movie) {
                view.showMovieDetails(movie);
            }

            @Override
            public void onFailure(String movie) {

            }
        });
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

    public void getMovieReviews() {
        Controller.getInstance().loadMovieReviews(movie.getId(), new Controller.RequestCallback<MovieReviews>() {
            @Override
            public void onResponse(MovieReviews movieReviews) {
                view.showMovieReviews(movieReviews.getReviews());
            }

            @Override
            public void onFailure(String message) {

            }
        });
    }

    public void getMovieVideos() {
        Controller.getInstance().loadMovieVideos(movie.getId(), new Controller.RequestCallback<MovieVideos>() {
            @Override
            public void onResponse(MovieVideos movieVideos) {
                view.showMovieVideos(movieVideos.getVideos());
            }

            @Override
            public void onFailure(String message) {

            }
        });
    }

}