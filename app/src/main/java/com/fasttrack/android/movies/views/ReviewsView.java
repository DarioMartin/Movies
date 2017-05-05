package com.fasttrack.android.movies.views;


import com.fasttrack.android.movies.models.Movie;
import com.fasttrack.android.movies.models.MovieReviews;

import java.util.List;

/**
 * Created by dariomartin on 3/4/17.
 */

public interface ReviewsView {
    void showReviews(List<MovieReviews.TMDBReview> reviews);
}
