package com.fasttrack.android.movies.views;


import com.fasttrack.android.movies.models.Movie;

import java.util.List;

/**
 * Created by dariomartin on 3/4/17.
 */

public interface MainView {
    void showMovies(List<Movie> movies);
}
