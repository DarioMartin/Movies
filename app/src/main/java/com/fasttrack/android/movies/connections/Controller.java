package com.fasttrack.android.movies.connections;

import com.fasttrack.android.movies.models.Movie;
import com.fasttrack.android.movies.models.MoviePage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by megamedia on 6/4/17.
 */

public class Controller {

    private static Controller instance;
    private static TheMovieDBAPI theMovieDBAPI;


    public Controller() {

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TheMovieDBAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        theMovieDBAPI = retrofit.create(TheMovieDBAPI.class);

    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public static void loadPopularMovies(int page, final RequestCallback callback) {
        theMovieDBAPI.loadPopularMovies(page).enqueue(new Callback<MoviePage>() {
            @Override
            public void onResponse(Call<MoviePage> call, Response<MoviePage> response) {
                if(response.code()==200){
                    callback.onResponse(response.body().getResults());
                }else{
                    callback.onFailure(response.message());
                }
            }

            @Override
            public void onFailure(Call<MoviePage> call, Throwable t) {

            }
        });
    }

    public interface RequestCallback<T> {
        void onResponse(T response);

        void onFailure(String message);
    }


}
