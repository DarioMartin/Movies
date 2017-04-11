package com.fasttrack.android.movies.views;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fasttrack.android.movies.R;
import com.fasttrack.android.movies.models.Movie;
import com.fasttrack.android.movies.models.MovieImages;
import com.fasttrack.android.movies.presenters.DetailsPresenter;
import com.fasttrack.android.movies.utils.ImageLoader;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DetailsActivity extends AppCompatActivity {

    private DetailsPresenter presenter;
    private ImageView poster, backdrop;
    private TextView releaseDate, rating, overview;
    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        movie = intent.getExtras().getParcelable("movie");

        Toolbar actionBarToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(actionBarToolbar);
        getSupportActionBar().setTitle(movie.getTitle());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        actionBarToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        presenter = new DetailsPresenter(this, movie);

        poster = (ImageView) findViewById(R.id.poster);
        backdrop = (ImageView) findViewById(R.id.backdrop);
        releaseDate = (TextView) findViewById(R.id.release_date);
        rating = (TextView) findViewById(R.id.rating);
        overview = (TextView) findViewById(R.id.overview);

        releaseDate.setText(dateFormatter(movie.getReleaseDate(), "yyyy-MM-dd", "MMMM dd, yyyy"));
        rating.setText(String.valueOf(movie.getVoteAverage()));
        overview.setText(movie.getOverview());

        presenter.getMovieImages();
    }

    public void showMovieImages(MovieImages movieImages) {
        if (!movieImages.getBackdrops().isEmpty()) {
            ImageLoader.loadImage(this, movieImages.getBackdrops().get(0).getURL(), backdrop);
        }

        ImageLoader.loadImage(this, movie.getMediumPoster(), poster);
    }

    private String dateFormatter(String originDate, String originFormat, String resultFormat) {
        try {
            DateFormat inputFormat = new SimpleDateFormat(originFormat, Locale.ENGLISH);
            DateFormat outputFormat = new SimpleDateFormat(resultFormat, Locale.ENGLISH);
            Date date = inputFormat.parse(originDate);
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return originDate;
    }
}
