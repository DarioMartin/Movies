package com.fasttrack.android.movies;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fasttrack.android.movies.models.MovieReviews;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.List;

/**
 * Created by dariomartin on 17/4/17.
 */

public class ReviewsModule extends LinearLayout {

    private LinearLayout reviewList;


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
    }

    public void addReviews(List<MovieReviews.TMDBReview> reviews) {
        if(reviews.isEmpty()){
            setVisibility(GONE);
            return;
        }

        LayoutInflater inflater = LayoutInflater.from(getContext());
        for (int i = 0; i < reviews.size(); i++) {
            View item = inflater.inflate(R.layout.review_item, null, false);
            TextView title = (TextView) item.findViewById(R.id.review_item_title);
            title.setText(reviews.get(i).getAuthor());
            ExpandableTextView content = (ExpandableTextView) item.findViewById(R.id.review_item_content);
            content.setText(reviews.get(i).getContent());
            item.setTag(i);
            reviewList.addView(item);
        }
    }

}
