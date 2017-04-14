package com.fasttrack.android.movies.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dariomartin on 6/4/17.
 */

public class MoviePage {

    @SerializedName("page")
    private long page;

    @SerializedName("results")
    private List<Movie> results = new ArrayList<>();

    @SerializedName("total_results")
    private long totalResults;

    @SerializedName("total_pages")
    private long totalPages;

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }

    public long getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(long totalResults) {
        this.totalResults = totalResults;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }
}
