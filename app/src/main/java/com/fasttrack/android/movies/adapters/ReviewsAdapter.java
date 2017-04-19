package com.fasttrack.android.movies.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fasttrack.android.movies.R;
import com.fasttrack.android.movies.models.MovieReviews;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dariomartin on 19/4/17.
 */

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ReviewViewHolder> {

    private ArrayList<MovieReviews.TMDBReview> reviews;

    public ReviewsAdapter() {
        this.reviews = new ArrayList<>();
    }

    public void clearContent() {
        reviews.clear();
    }


    @Override
    public ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_item, parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReviewViewHolder holder, final int position) {
        holder.setItem(reviews.get(position));
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public void addReviews(List<MovieReviews.TMDBReview> reviews) {
        this.reviews.addAll(reviews);
    }


    class ReviewViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private ExpandableTextView reviewContent;

        ReviewViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.review_item_title);
            reviewContent = (ExpandableTextView) itemView.findViewById(R.id.review_item_content);
        }

        void setItem(MovieReviews.TMDBReview review) {
            title.setText(review.getAuthor());
            reviewContent.setText(review.getContent());
        }
    }
}
