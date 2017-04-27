package com.fasttrack.android.movies.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by megamedia on 27/4/17.
 */

public class MoviesContract {

    public static final String AUTHORITY = "com.fasttrack.android.movies";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    public static final String PATH_MOVIES = "movies";

    public static final class FavMoviesEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_MOVIES).build();

        public static final String TABLE_NAME = "favmovies";

        public static final String COLUMN_TMDB_ID = "tmdbid";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_POSTER = "poster";

    }

}
