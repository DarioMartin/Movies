package com.fasttrack.android.movies;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fasttrack.android.movies.adapters.ReviewsAdapter;
import com.fasttrack.android.movies.models.MovieReviews;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dariomartin on 17/4/17.
 */

public class ReviewsModule extends LinearLayout {

    private View rootView;

    private LinearLayout reviewList;
    private List<MovieReviews.TMDBReview> reviews;
    private TextView showAllOption;


    public ReviewsModule(Context context) {
        super(context);
        init(context);
    }

    public ReviewsModule(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.reviews_module, this);
        reviewList = (LinearLayout) findViewById(R.id.reviews_linear_layout);
        showAllOption = (TextView) findViewById(R.id.show_all_button);
    }

    public void addReviews(List<MovieReviews.TMDBReview> reviews, int max, OnClickListener showAllClickListener) {

        LayoutInflater inflater = LayoutInflater.from(getContext());

        this.reviews = reviews;

        for (int i = 0; i < reviews.size(); i++) {
            View item = inflater.inflate(R.layout.review_item, null, false);
            TextView title = (TextView) item.findViewById(R.id.review_item_title);
            title.setText(reviews.get(i).getAuthor());
            ExpandableTextView content = (ExpandableTextView) item.findViewById(R.id.review_item_content);
            content.setText(reviews.get(i).getContent());
            item.setTag(i);
            reviewList.addView(item);
            if (i >= max) break;
        }

        showAllOption.setVisibility(showAllClickListener != null ? VISIBLE : GONE);
        showAllOption.setOnClickListener(showAllClickListener);

    }

}
