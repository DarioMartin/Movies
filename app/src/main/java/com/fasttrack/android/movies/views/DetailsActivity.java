package com.fasttrack.android.movies.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.fasttrack.android.movies.R;
import com.fasttrack.android.movies.models.Movie;
import com.fasttrack.android.movies.models.MovieImages;
import com.fasttrack.android.movies.presenters.DetailsPresenter;
import com.fasttrack.android.movies.utils.ImageLoader;

public class DetailsActivity extends AppCompatActivity {

    private DetailsPresenter presenter;
    private ImageView poster;
    private TextView title, releaseDate, rating, overview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        Movie movie = intent.getExtras().getParcelable("movie");

        presenter = new DetailsPresenter(this, movie);

        poster = (ImageView) findViewById(R.id.poster);
        title = (TextView) findViewById(R.id.title);
        releaseDate = (TextView) findViewById(R.id.release_date);
        rating = (TextView) findViewById(R.id.rating);
        overview = (TextView) findViewById(R.id.overview);

        title.setText(movie.getTitle());
        releaseDate.setText(movie.getReleaseDate());
        rating.setText(String.valueOf(movie.getVoteAverage()));
        overview.setText(movie.getOverview());

        presenter.getMovieImages();
    }

    public void showMovieImages(MovieImages movieImages) {
        if (!movieImages.getBackdrops().isEmpty()) {
            ImageLoader.loadImage(this, movieImages.getBackdrops().get(0).getURL(), poster);
        }
    }
}
