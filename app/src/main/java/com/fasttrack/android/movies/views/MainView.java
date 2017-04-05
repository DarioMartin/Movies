package com.fasttrack.android.movies.views;


import com.fasttrack.android.movies.models.Movie;

import java.util.ArrayList;

/**
 * Created by dariomartin on 3/4/17.
 */

public interface MainView {
    void showMovies(ArrayList<Movie> movies);
}
