package com.fasttrack.android.movies;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.fasttrack.android.movies.adapters.VideosAdapter;
import com.fasttrack.android.movies.models.MovieVideos;

import java.util.List;

/**
 * Created by dariomartin on 17/4/17.
 */

public class VideosModule extends LinearLayout {

    private ListView videoList;

    public VideosModule(Context context) {
        super(context);
        init(context);
    }

    public VideosModule(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.videos_module, this);
        videoList = (ListView) findViewById(R.id.video_list_view);
    }

    public void addVideos(List<MovieVideos.TMDBVideo> videos) {
        VideosAdapter adapter = new VideosAdapter(getContext(), videos);
        videoList.setAdapter(adapter);
    }

}
