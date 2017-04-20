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

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIso_639_1() {
            return iso_639_1;
        }

        public void setIso_639_1(String iso_639_1) {
            this.iso_639_1 = iso_639_1;
        }

        public String getIso_3166_1() {
            return iso_3166_1;
        }

        public void setIso_3166_1(String iso_3166_1) {
            this.iso_3166_1 = iso_3166_1;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSite() {
            return site;
        }

        public void setSite(String site) {
            this.site = site;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public List<TMDBVideo> getVideos() {
        return videos;
    }

    public void setVideos(List<TMDBVideo> videos) {
        this.videos = videos;
    }
}
