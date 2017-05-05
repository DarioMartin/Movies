package com.fasttrack.android.movies.presenters;

import com.fasttrack.android.movies.connections.Controller;
import com.fasttrack.android.movies.models.Movie;
import com.fasttrack.android.movies.models.MovieReviews;
import com.fasttrack.android.movies.views.MainView;
import com.fasttrack.android.movies.views.ReviewsView;

import java.util.List;

/**
 * Created by dariomartin on 3/4/17.
 */

public class ReviewsPresenter {

    private ReviewsView view;
    private String movieId;

    public ReviewsPresenter(ReviewsView view, String movieId) {
        this.view = view;
        this.movieId = movieId;
    }

    public void getReviews(int currentPage) {
        Controller.getInstance().loadMovieReviews(currentPage, movieId, new Controller.RequestCallback<MovieReviews>() {
            @Override
            public void onResponse(MovieReviews movieReviews) {
                view.showReviews(movieReviews.getReviews());
            }

            @Override
            public void onFailure(String message) {

            }
        });
    }
}
