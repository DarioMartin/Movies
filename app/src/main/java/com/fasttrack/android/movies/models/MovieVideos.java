package com.fasttrack.android.movies.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dariomartin on 17/4/17.
 */

public class MovieVideos {

    /*{
id: 100,
results: [
{
id: "533ec652c3a36854480000c3",
iso_639_1: "en",
iso_3166_1: "US",
key: "h6hZkvrFIj0",
name: "Trailer 1",
site: "YouTube",
size: 360,
type: "Trailer"
}
]
}*/

    @SerializedName("results")
    private List<TMDBVideo> videos = new ArrayList<>();

    public class TMDBVideo {

        @SerializedName("id")
        String id;

        @SerializedName("iso_639_1")
        String iso_639_1;

        @SerializedName("iso_3166_1")
        String iso_3166_1;

        @SerializedName("key")
        String key;

        @SerializedName("name")
        String name;

        @SerializedName("site")
        String site;

        @SerializedName("size")
        int size;

        @SerializedName("type")
        String type;
    }

    public List<TMDBVideo> getVideos() {
        return videos;
    }

    public void setVideos(List<TMDBVideo> videos) {
        this.videos = videos;
    }
}
