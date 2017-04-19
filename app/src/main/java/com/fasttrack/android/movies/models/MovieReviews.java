package com.fasttrack.android.movies.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dariomartin on 17/4/17.
 */

public class MovieReviews {

    /*{
id: 100,
page: 1,
results: [
{
id: "529bc23719c2957215011e7b",
author: "BradFlix",
content: "I just plain love this movie!",
url: "https://www.themoviedb.org/review/529bc23719c2957215011e7b"
}
],
total_pages: 1,
total_results: 3
}*/

    @SerializedName("results")
    private List<TMDBReview> reviews = new ArrayList<>();

    public List<TMDBReview> getReviews() {
        return reviews;
    }

    public void setReviews(List<TMDBReview> reviews) {
        this.reviews = reviews;
    }

    public class TMDBReview {

        @SerializedName("id")
        String id;

        @SerializedName("author")
        String author;

        @SerializedName("content")
        String content;

        @SerializedName("url")
        String url;

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getId() {

            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
