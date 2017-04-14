package com.fasttrack.android.movies.presenters;

import com.fasttrack.android.movies.connections.Controller;
import com.fasttrack.android.movies.models.Movie;
import com.fasttrack.android.movies.views.MainView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dariomartin on 3/4/17.
 */

public class MainPresenter {

    public enum Sorting {
        POPULAR, TOP_RATED
    }

    private MainView view;

    public MainPresenter(MainView view) {
        this.view = view;
    }

    public void getMovies(Sorting sorting) {
        getMovies(1, sorting);
    }

    public void getMovies(int current_page, Sorting sorting) {
        switch (sorting) {
            case POPULAR:
                Controller.getInstance().loadPopularMovies(current_page, new Controller.RequestCallback<List<Movie>>() {
                    @Override
                    public void onResponse(List<Movie> movies) {
                        view.showMovies(movies);
                    }

                    @Override
                    public void onFailure(String message) {

                    }
                });
                break;
            case TOP_RATED:
                Controller.getInstance().loadTopRatedMovies(current_page, new Controller.RequestCallback<List<Movie>>() {
                    @Override
                    public void onResponse(List<Movie> movies) {
                        view.showMovies(movies);
                    }

                    @Override
                    public void onFailure(String message) {

                    }
                });
        }
    }
}
