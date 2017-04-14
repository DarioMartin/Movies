package com.fasttrack.android.movies.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dariomartin on 6/4/17.
 */

public class MovieImages {

    private static final String BASE_IMAGE_URL = "http://image.tmdb.org/t/p/";

    @SerializedName("backdrops")
    private List<TMDBImage> backdrops = new ArrayList<>();

    @SerializedName("posters")
    private List<TMDBImage> posters = new ArrayList<>();

    public List<TMDBImage> getBackdrops() {
        return backdrops;
    }

    public void setBackdrops(List<TMDBImage> backdrops) {
        this.backdrops = backdrops;
    }

    public class TMDBImage {

        @SerializedName("file_path")
        String filePath;

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        public String getURL() {
            return BASE_IMAGE_URL + "/w500/" +  filePath;

        }
    }
}
