package com.fasttrack.android.movies.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.fasttrack.android.movies.R;
import com.fasttrack.android.movies.models.Movie;
import com.fasttrack.android.movies.presenters.MainPresenter;
import com.fasttrack.android.movies.utils.EndlessRecyclerOnScrollListener;
import com.fasttrack.android.movies.views.adapters.MoviesAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView {

    private RecyclerView moviesRecyclerView;

    private MoviesAdapter adapter;

    private GridLayoutManager layoutManager;

    private MainPresenter presenter;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    return true;
                case R.id.navigation_dashboard:
                    return true;
                case R.id.navigation_notifications:
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setVisibility(View.GONE);

        moviesRecyclerView = (RecyclerView) findViewById(R.id.moviesRecyclreView);
        moviesRecyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(this, 2);
        moviesRecyclerView.setLayoutManager(layoutManager);

        adapter = new MoviesAdapter();
        adapter.setPositionClickListener(new MoviesAdapter.PositionClickListener() {
            @Override
            public void itemClicked(int position) {
                Movie movie = adapter.getMovieAtPositon(position);
                Intent myIntent = new Intent(MainActivity.this, DetailsActivity.class);
                myIntent.putExtra("movie", movie);
                MainActivity.this.startActivity(myIntent);
            }
        });

        moviesRecyclerView.setAdapter(adapter);

        presenter = new MainPresenter(this);

        moviesRecyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(layoutManager, 10) {
            @Override
            public void onLoadMore(int current_page) {
                presenter.getMovies(current_page);
            }
        });

        presenter.getMovies();
    }


    @Override
    public void showMovies(List<Movie> movies) {
        adapter.addMovies(movies);
        adapter.notifyDataSetChanged();
    }
}
