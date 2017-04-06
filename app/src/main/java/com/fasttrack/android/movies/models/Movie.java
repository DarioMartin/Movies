package com.fasttrack.android.movies.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by dariomartin on 3/4/17.
 */

public class Movie {

    private static final String BASE_IMAGE_URL = "http://image.tmdb.org/t/p/";

    @SerializedName("adult")
    boolean adultContent;

    @SerializedName("backdrop_path")
    boolean backdrop;

    @SerializedName("genre_ids")
    ArrayList<String> genresIds;

    @SerializedName("id")
    String id;

    @SerializedName("original_language")
    String originalLanguage;

    @SerializedName("original_title")
    String originalTitle;

    @SerializedName("overview")
    String overview;

    @SerializedName("popularity")
    String popularity;

    @SerializedName("poster_path")
    String poster;

    @SerializedName("release_date")
    String releaseDate;

    @SerializedName("title")
    String title;

    @SerializedName("video")
    boolean video;

    @SerializedName("vote_average")
    double voteAverage;

    @SerializedName("vote_count")
    int voteCount;

    public Movie(String title) {
        this.title = title;
    }

    public boolean isAdultContent() {
        return adultContent;
    }

    public void setAdultContent(boolean adultContent) {
        this.adultContent = adultContent;
    }

    public boolean isBackdrop() {
        return backdrop;
    }

    public void setBackdrop(boolean backdrop) {
        this.backdrop = backdrop;
    }

    public ArrayList<String> getGenresIds() {
        return genresIds;
    }

    public void setGenresIds(ArrayList<String> genresIds) {
        this.genresIds = genresIds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getPoster() {
        return BASE_IMAGE_URL+"/w342/" + poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }
}
