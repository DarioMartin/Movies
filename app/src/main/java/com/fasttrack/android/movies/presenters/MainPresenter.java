package com.fasttrack.android.movies.presenters;

import android.database.Cursor;

import com.fasttrack.android.movies.connections.Controller;
import com.fasttrack.android.movies.data.MoviesContract;
import com.fasttrack.android.movies.models.Movie;
import com.fasttrack.android.movies.views.MainView;

import java.util.List;

/**
 * Created by dariomartin on 3/4/17.
 */

public class MainPresenter {

    public enum Sorting {
        POPULAR, FAVS, TOP_RATED
    }

    private MainView view;

    public MainPresenter(MainView view) {
        this.view = view;
    }

    public void getMovies(Sorting sorting) {
        getMovies(1, sorting);
    }

    public void getMovies(int currentPage, Sorting sorting) {
        switch (sorting) {
            case POPULAR:
                Controller.getInstance().loadPopularMovies(currentPage, new Controller.RequestCallback<List<Movie>>() {
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
                Controller.getInstance().loadTopRatedMovies(currentPage, new Controller.RequestCallback<List<Movie>>() {
                    @Override
                    public void onResponse(List<Movie> movies) {
                        view.showMovies(movies);
                    }

                    @Override
                    public void onFailure(String message) {

                    }
                });
            case FAVS:
                if (currentPage == 1) {
                    view.showDBMovies();
                }
        }
    }
}
