package com.fasttrack.android.movies.connections;

import com.fasttrack.android.movies.BuildConfig;
import com.fasttrack.android.movies.models.MovieImages;
import com.fasttrack.android.movies.models.MoviePage;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by dariomartin on 6/4/17.
 */

public interface TheMovieDBAPI {

    String BASE_URL = "https://api.themoviedb.org/3/";

    @GET("movie/popular?api_key=" + BuildConfig.THE_MOVIE_DB_API_KEY)
    Call<MoviePage> getPopularMovies(@Query("page") int page);

    @GET("movie/top_rated?api_key=" + BuildConfig.THE_MOVIE_DB_API_KEY)
    Call<MoviePage> getTopRatedMovies(@Query("page") int page);

    @GET("movie/{id}/images?api_key=" + BuildConfig.THE_MOVIE_DB_API_KEY)
    Call<MovieImages> getMovieImages(@Path("id") String id);


}
