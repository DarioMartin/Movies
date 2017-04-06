package com.fasttrack.android.movies.connections;

import com.fasttrack.android.movies.BuildConfig;
import com.fasttrack.android.movies.models.MoviePage;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by megamedia on 6/4/17.
 */

public interface TheMovieDBAPI {

    String BASE_URL = "https://api.themoviedb.org/3/";

    @GET("discover/movie/?sort_by=popularity.desc&api_key=" + BuildConfig.THE_MOVIE_DB_API_KEY)
    Call<MoviePage> loadPopularMovies(@Query("page") int page);

}
