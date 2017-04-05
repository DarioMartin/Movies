package com.fasttrack.android.movies.presenters;

import com.fasttrack.android.movies.models.Movie;
import com.fasttrack.android.movies.views.MainView;

import java.util.ArrayList;

/**
 * Created by dariomartin on 3/4/17.
 */

public class MainPresenter {

    private MainView view;

    public MainPresenter(MainView view) {
        this.view = view;
    }

    public void getMovies() {
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Peli 1"));
        movies.add(new Movie("Peli 2"));
        movies.add(new Movie("Peli 3"));
        movies.add(new Movie("Peli 4"));
        movies.add(new Movie("Peli 5"));
        view.showMovies(movies);
    }
}
