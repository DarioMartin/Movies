package com.fasttrack.android.movies.views;

import com.fasttrack.android.movies.models.Movie;
import com.fasttrack.android.movies.models.MovieImages;
import com.fasttrack.android.movies.models.MovieReviews;
import com.fasttrack.android.movies.models.MovieVideos;

import java.util.List;

/**
 * Created by dariomartin on 17/4/17.
 */

public interface DetailsView {

    void showMovieImages(MovieImages movieImages);

    void showMovieVideos(List<MovieVideos.TMDBVideo> movieVideos);

    void showMovieReviews(List<MovieReviews.TMDBReview> movieReviews);

    void showMovieDetails(Movie movie);

    void updateFavState(boolean isFavourite);
}
