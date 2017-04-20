package com.fasttrack.android.movies;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.fasttrack.android.movies.adapters.VideosAdapter;
import com.fasttrack.android.movies.models.MovieVideos;

import java.util.List;

/**
 * Created by dariomartin on 17/4/17.
 */

public class VideosModule extends LinearLayout {

    private View rootView;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private VideosAdapter adapter;

    public VideosModule(Context context) {
        super(context);
        init(context);
    }

    public VideosModule(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        rootView = inflate(context, R.layout.videos_module, this);

        recyclerView = (RecyclerView) findViewById(R.id.videos_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new VideosAdapter();
        recyclerView.setAdapter(adapter);
    }

    public void addVideos(List<MovieVideos.TMDBVideo> videos) {
        adapter.addVideos(videos);
        adapter.notifyDataSetChanged();
    }

}
