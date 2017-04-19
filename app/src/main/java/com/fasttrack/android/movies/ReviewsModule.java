package com.fasttrack.android.movies;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.fasttrack.android.movies.adapters.ReviewsAdapter;
import com.fasttrack.android.movies.models.MovieReviews;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dariomartin on 17/4/17.
 */

public class ReviewsModule extends LinearLayout {

    private View rootView;
    private List<MovieReviews.TMDBReview> reviews = new ArrayList<>();
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private ReviewsAdapter adapter;

    public ReviewsModule(Context context) {
        super(context);
        init(context);
    }

    public ReviewsModule(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        rootView = inflate(context, R.layout.reviews_module, this);

        recyclerView = (RecyclerView) findViewById(R.id.reviews_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ReviewsAdapter();
        recyclerView.setAdapter(adapter);
    }

    public void addReviews(List<MovieReviews.TMDBReview> reviews) {
        adapter.addReviews(reviews);
        adapter.notifyDataSetChanged();
    }

}
