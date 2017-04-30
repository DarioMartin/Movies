package com.fasttrack.android.movies.views;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.fasttrack.android.movies.R;
import com.fasttrack.android.movies.adapters.MoviesAdapter;
import com.fasttrack.android.movies.data.MoviesContract;
import com.fasttrack.android.movies.models.Movie;
import com.fasttrack.android.movies.presenters.MainPresenter;
import com.fasttrack.android.movies.utils.EndlessRecyclerOnScrollListener;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView {

    private MainPresenter.Sorting sorting = MainPresenter.Sorting.POPULAR;
    private RecyclerView moviesRecyclerView;
    private MoviesAdapter adapter;
    private GridLayoutManager layoutManager;
    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moviesRecyclerView = (RecyclerView) findViewById(R.id.movies_recycler_view);
        moviesRecyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(this, 2);
        moviesRecyclerView.setLayoutManager(layoutManager);

        adapter = new MoviesAdapter();
        adapter.setPositionClickListener(new MoviesAdapter.PositionClickListener() {
            @Override
            public void itemClicked(int position) {
                Movie movie = adapter.getMovieAtPosition(position);
                if (movie != null) {
                    Intent myIntent = new Intent(MainActivity.this, DetailsActivity.class);
                    myIntent.putExtra("movie", movie);
                    MainActivity.this.startActivity(myIntent);
                }
            }
        });

        moviesRecyclerView.setAdapter(adapter);

        presenter = new MainPresenter(this);

        updateContent();
    }


    @Override
    public void showMovies(List<Movie> movies) {
        adapter.addMovieList(movies);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showDBMovies() {
        Cursor movieCursor = getContentResolver().query(MoviesContract.FavMoviesEntry.CONTENT_URI, null, null, null, null);
        adapter.addMovieCursor(movieCursor);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sorting, menu);
        setTitle(menu.getItem(0).getTitle());
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemSelected = item.getItemId();
        MainPresenter.Sorting newSorting = MainPresenter.Sorting.POPULAR;
        switch (itemSelected) {
            case R.id.sorting_popular:
                newSorting = MainPresenter.Sorting.POPULAR;
                break;
            case R.id.sorting_top_rated:
                newSorting = MainPresenter.Sorting.TOP_RATED;
                break;
            case R.id.sorting_favs:
                newSorting = MainPresenter.Sorting.FAVS;
                break;
        }
        if (newSorting != sorting) {
            sorting = newSorting;
            updateContent();
        }

        setTitle(item.getTitle());

        return super.onOptionsItemSelected(item);
    }

    private void updateContent() {
        moviesRecyclerView.clearOnScrollListeners();
        moviesRecyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(layoutManager, 10) {
            @Override
            public void onLoadMore(int current_page) {
                presenter.getMovies(current_page, sorting);
            }
        });
        adapter.clearContent();
        presenter.getMovies(sorting);
    }
}
