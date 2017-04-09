package com.fasttrack.android.movies.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

public class DetailsActivity extends AppCompatActivity {

    private DetailsPresenter presenter;
    private ImageView poster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        Movie movie = intent.getExtras().getParcelable("movie");

        presenter = new DetailsPresenter(this, movie);

         poster = (ImageView) findViewById(R.id.poster);

        TextView titleTV = (TextView) findViewById(R.id.title);
        titleTV.setText(movie.getTitle());

        presenter.getMovieImages();
    }

    public void showMovieImages(MovieImages movieImages) {
        if(!movieImages.getBackdrops().isEmpty()){
            ImageLoader.loadImage(this, movieImages.getBackdrops().get(0).getURL(), poster);
        }
    }
}
