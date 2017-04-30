package com.fasttrack.android.movies.views;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.fasttrack.android.movies.R;
import com.fasttrack.android.movies.adapters.MoviesAdapter;
import com.fasttrack.android.movies.data.MoviesContract;
import com.fasttrack.android.movies.models.Movie;
import com.fasttrack.android.movies.presenters.MainPresenter;
import com.fasttrack.android.movies.utils.EndlessRecyclerOnScrollListener;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView, LoaderManager.LoaderCallbacks<Cursor> {

    private static final int FAV_MOVIES_LOADER = 45;

    private MainPresenter.Sorting sorting = MainPresenter.Sorting.POPULAR;
    private RecyclerView moviesRecyclerView;
    private MoviesAdapter adapter;
    private GridLayoutManager layoutManager;
    private MainPresenter presenter;
    private TextView noContentMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noContentMessage = (TextView) findViewById(R.id.no_content_message);

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
        getSupportLoaderManager().destroyLoader(FAV_MOVIES_LOADER);
        adapter.addMovieList(movies);
        noContentMessage.setVisibility(movies.isEmpty() ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showDBMovies() {
        getSupportLoaderManager().initLoader(FAV_MOVIES_LOADER, null, this);
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

    @Override
    public Loader<Cursor> onCreateLoader(int loaderId, Bundle args) {
        switch (loaderId) {
            case FAV_MOVIES_LOADER:
                return new CursorLoader(this, MoviesContract.FavMoviesEntry.CONTENT_URI, null, null, null, null);
            default:
                throw new RuntimeException("Loader Not Implemented: " + loaderId);
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.addMovieCursor(data);
        noContentMessage.setVisibility(data.getCount() == 0 ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.addMovieCursor(null);
        noContentMessage.setVisibility(View.VISIBLE);
    }
}
