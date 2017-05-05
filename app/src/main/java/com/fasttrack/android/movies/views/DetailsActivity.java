package com.fasttrack.android.movies.views;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.fasttrack.android.movies.R;
import com.fasttrack.android.movies.ReviewsModule;
import com.fasttrack.android.movies.VideosModule;
import com.fasttrack.android.movies.data.MoviesContract;
import com.fasttrack.android.movies.models.Movie;
import com.fasttrack.android.movies.models.MovieImages;
import com.fasttrack.android.movies.models.MovieReviews;
import com.fasttrack.android.movies.models.MovieVideos;
import com.fasttrack.android.movies.presenters.DetailsPresenter;
import com.fasttrack.android.movies.utils.ImageLoader;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DetailsActivity extends AppCompatActivity implements DetailsView {

    public static final String MOVIE = "movie";

    private DetailsPresenter presenter;
    private ImageView poster;
    private TextView releaseDate, rating, overview;
    private ToggleButton favouriteToggle;
    private Movie movie;
    private CustomPagerAdapter backdropAdapter;
    private ReviewsModule reviewsModule;
    private VideosModule videosModule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        movie = intent.getExtras().getParcelable(MOVIE);

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

        ViewPager viewPager = (ViewPager) findViewById(R.id.backdrop_view_pager);
        backdropAdapter = new CustomPagerAdapter(getApplicationContext());
        viewPager.setAdapter(backdropAdapter);
        poster = (ImageView) findViewById(R.id.poster);
        releaseDate = (TextView) findViewById(R.id.release_date);
        rating = (TextView) findViewById(R.id.rating);
        favouriteToggle = (ToggleButton) findViewById(R.id.favourite_toogle);
        overview = (TextView) findViewById(R.id.overview);
        reviewsModule = (ReviewsModule) findViewById(R.id.reviews_module);
        videosModule = (VideosModule) findViewById(R.id.videos_module);

        presenter.getMovieDetails();
        presenter.getMovieImages();
        presenter.getMovieReviews();
        presenter.getMovieVideos();

        configureFavToggle();
    }

    private void configureFavToggle() {
        Uri uri = MoviesContract.FavMoviesEntry.CONTENT_URI;
        uri = uri.buildUpon().appendPath(movie.getId()).build();

        Cursor favCursor = getContentResolver().query(uri, null, null, null, null);

        favouriteToggle.setChecked(favCursor.getCount() > 0);

        favouriteToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (favouriteToggle.isChecked()) {
                    addFavMovie();
                } else {
                    deleteFavMovie();
                }
            }
        });
    }

    private void deleteFavMovie() {
        try {
            Uri uriToDelete = MoviesContract.FavMoviesEntry.CONTENT_URI.buildUpon().appendPath(movie.getId()).build();
            int movieDeleted = getContentResolver().delete(uriToDelete, null, null);
            if (movieDeleted > 0) {
                Toast.makeText(getBaseContext(), getString(R.string.movie_deleted), Toast.LENGTH_SHORT).show();
            }
        } catch (android.database.SQLException exception) {
        }
    }

    private void addFavMovie() {
        ContentValues movieValues = new ContentValues();
        movieValues.put(MoviesContract.FavMoviesEntry.COLUMN_TITLE, movie.getTitle());
        movieValues.put(MoviesContract.FavMoviesEntry.COLUMN_TMDB_ID, movie.getId());
        movieValues.put(MoviesContract.FavMoviesEntry.COLUMN_POSTER, movie.getPoster());

        try {
            Uri uri = getContentResolver().insert(MoviesContract.FavMoviesEntry.CONTENT_URI, movieValues);
            if (uri != null) {
                Toast.makeText(getBaseContext(), getString(R.string.movie_saved), Toast.LENGTH_SHORT).show();
            }
        } catch (android.database.SQLException exception) {
        }
    }

    @Override
    public void showMovieDetails(Movie movie) {
        releaseDate.setText(dateFormatter(movie.getReleaseDate(), "yyyy-MM-dd", "MMMM dd, yyyy"));
        rating.setText(String.valueOf(movie.getVoteAverage()));
        overview.setText(movie.getOverview());
    }

    public void showMovieImages(MovieImages movieImages) {
        backdropAdapter.addImages(movieImages.getBackdrops());
        backdropAdapter.notifyDataSetChanged();
        ImageLoader.loadImage(this, movie.getMediumPoster(), poster);
    }

    @Override
    public void showMovieVideos(List<MovieVideos.TMDBVideo> movieVideos) {
        videosModule.addVideos(movieVideos);
    }

    @Override
    public void showMovieReviews(List<MovieReviews.TMDBReview> movieReviews) {
        reviewsModule.addReviews(movieReviews);
    }

    @Override
    public void updateFavState(boolean isFavourite) {
        favouriteToggle.setChecked(isFavourite);
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

    class CustomPagerAdapter extends PagerAdapter {

        Context context;
        LayoutInflater mLayoutInflater;
        List<MovieImages.TMDBImage> images = new ArrayList<>();

        public CustomPagerAdapter(Context context) {
            this.context = context;
            mLayoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public void addImages(List<MovieImages.TMDBImage> images) {
            this.images.addAll(images);
        }

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);

            ImageView imageView = (ImageView) itemView.findViewById(R.id.view_pager_image);
            ImageLoader.loadImage(context, images.get(position).getURL(), imageView);

            container.addView(itemView);

            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout) object);
        }
    }
}
