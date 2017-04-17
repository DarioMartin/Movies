package com.fasttrack.android.movies;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by dariomartin on 17/4/17.
 */

public class ReviewsModule extends LinearLayout {

    private View rootView;

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

    }


}
